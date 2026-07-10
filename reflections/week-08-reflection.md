# Week {{N}} Reflection

**Name:** Mai Moua
**Date:** 07/09/2026

---

## Commits This Week


**Link:** https://github.com/mmoua02/media-tracker-android/pull/9

---

## Code Review

**Reviewed:** Ryan Burke
**Link to my review:** https://github.com/oppenrhymer/media-tracker-android/pull/9#pullrequestreview-4673159948

### What I Looked At

For the code that I looked at, it focused on implementing API data retrieval for the media tracking application.
The PR showed functionality to fetch media details, check if items exist in the user's library, and retrieve reviews.
I focused on reviewing the SearchResultsViewModel, LibraryResultsViewModel, and ReviewResultsRepository, to check the
logics and see how the calls were being handled.

### What I Noticed

In the SearchResultsViewModel, I noticed the potential for the UI to just hang if a call fails, so I suggested
adding an error handling within the try-catch block. In the LibraryResultsRepository, I liked
the check for the 404 error, but I also recommend that as the app grows in the future, maybe using a when block
would be a cleaner approach to handle various status codes. I also noticed some Log.d statements
left in the repository classes for debugging.

### Comments I Left

I left a comment in the SearchResultsModelView, suggesting that an error handling can be added in so that the
UI doesn't stop responding. For the LibraryResultsRepository, I praised the 404 handling and suggested
using a when block for future proofing the code. I also noted that the logging was helpful in the ReviewResultsRepository
for tracing. And then I just left a positive note noting the clean implementation or @Serializable to make the JSON
easier to maintain.

---

## One Thing I Understood More Deeply

I gained a deeper unstanding of how to handle data flow from an API into the app's architecture. Specifically,
transitioning from manual JSON parsing to using @Serializable. It made the data layer feel uch more robust.
Seeing how repositories can cleanly manage these calls and how they translate the responses into
states that the UI can use. I think this is a good visualization to me about the entire data from the backend.

---

## One Thing I'm Still Confused About

I am still trying to figure out the best practices for handling complex error states across the repositories.
While I did implement a 404 check, I am not entirely sure if that if the most efficient way yet without cluttering the ViewModels.

---

## Anything Else *(optional)*

I have been spending some time reviewing both of my pod mates PR, and it was helpful to learn and see how they approached their own setups.
It is cool to see that we all are approaching it with a similar design decision, even if our implementations were different.

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
