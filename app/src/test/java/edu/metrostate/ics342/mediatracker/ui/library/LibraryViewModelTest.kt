package edu.metrostate.ics342.mediatracker.ui.library

import android.app.Application
import edu.metrostate.ics342.mediatracker.data.model.LibraryItem
import edu.metrostate.ics342.mediatracker.data.model.LibraryStatus
import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.network.MediaApiService
import edu.metrostate.ics342.mediatracker.data.network.RetrofitInstance
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class LibraryViewModelTest {

    private lateinit var viewModel: LibraryViewModel
    private val app = mockk<Application>(relaxed = true)
    private val api = mockk<MediaApiService>()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockkObject(RetrofitInstance)
        every { RetrofitInstance.mediaApiService(any()) } returns api
        
        // Mock initial load
        coEvery { api.getLibrary() } returns Response.success(emptyList())
        
        viewModel = LibraryViewModel(app)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `removeItem rolls back on failure`() = runTest {
        // Given a library with one item
        val mediaId = 1
        val item = LibraryItem(
            userId = "user1",
            mediaId = mediaId,
            status = LibraryStatus.WANT_TO,
            addedAt = "",
            updatedAt = "",
            media = Media(id = mediaId, title = "Test Media", mediaType = "book")
        )
        
        // Inject initial data by mocking loadLibrary again or setting it
        coEvery { api.getLibrary() } returns Response.success(listOf(item))
        viewModel.loadLibrary()
        testDispatcher.scheduler.advanceUntilIdle()
        
        assertEquals(1, viewModel.libraryItems.value.size)

        // When removeItem fails
        coEvery { api.removeFromLibrary(mediaId) } throws Exception("Network error")
        
        viewModel.removeItem(mediaId)
        
        // Assert optimistic update (item gone)
        assertTrue(viewModel.libraryItems.value.none { it.mediaId == mediaId })
        
        // Wait for coroutine
        testDispatcher.scheduler.advanceUntilIdle()

        // Then verify rollback (item returned)
        assertEquals(1, viewModel.libraryItems.value.size)
        assertEquals(mediaId, viewModel.libraryItems.value[0].mediaId)
        assertEquals("Couldn't remove item. Try again.", viewModel.errorMessage.value)
    }
}
