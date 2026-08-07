package edu.metrostate.ics342.mediatracker.ui.quotes

import edu.metrostate.ics342.mediatracker.data.model.Quote
import edu.metrostate.ics342.mediatracker.data.network.QuotePage
import edu.metrostate.ics342.mediatracker.data.network.QuoteRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class QuoteViewModelTest {

    private lateinit var viewModel: QuoteViewModel
    private val repository: QuoteRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

    }

    @Test
    fun `loadQuotes updates state to Success with items`() = runTest {
        val quotes = listOf(
            Quote(id = 1, mediaId = 1, text = "Test Quote", userId = "1")
        )
        coEvery { repository.getQuotes(any(), any()) } returns QuotePage(quotes, null, false)
    }
}
