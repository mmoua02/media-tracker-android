# Week 12 Reflection — Bonus Feature Sprint (Week 2 of 2, Final)


**Name:** Mai Moua

**Date:** 08/06/2026

**My assigned bonus feature:** Quotes

---

## Commits This Week

**Link:** https://github.com/mmoua02/media-tracker-android/pull/12

---

## Code Review

**Reviewed:** Ryan Burke

**Link to my review:** https://github.com/oppenrhymer/media-tracker-android/pull/12#pullrequestreview-4879448459

### What I Looked At

In this review, I looked at the implementation of the update and delete features across the multiple layers.
This includes looking at the new data transfer object and the updated API service that hosted the 
@PUT and @DELETE. I also took a look at the wrappers that were added to the ReviewResultRepository.kt
along with the UI updates that were made.

### What I Noticed

I noticed that the UI implementations were good in the MediaDetailViewModel.kt.
During the review deletion, it temporarily filters out the item and restore it if the network throws an exception.
The tracking state also checks if the user has already submitted a review.

### Comments I Left

For my comments, I left feed back commenting about the nice implementation of the UI update, but I also 
suggested that maybe a Snack bar could be added to give users a visual confirmation of when a review is deleted.
And then I commented about the AlertDialog, which I think handles the cases nicely by dismissing clicks and presses.

---

## Bonus Feature — Final Status

**What works end-to-end, right now:**

Users can navigate to a new "Quotes" tab from the bottom navigation bar to view their personal quotes
or a feed of public quotes from the Media Detail Screen. I have implemented the CRUD operation; 
save new quotes, get the list, edit text/privacy setting, and deleting quotes with a confirmation dialog.

**Tests written for this feature:**

I created a test in QuoteViewModelTest.kt to test the QuoteViewModel, I specifically wanted to test 
that the toggle for the "Like" toggles correctly and handles the response.

**Known gaps or rough edges going into demos:**

A persistent issue is with the authentication token. While the logic is wired, saving a quote is failing
because the app is not registering the access token. I am still working on this.

---

## One Thing I Understood More Deeply

I gained a deeper understanding about the importance of data mapping and serialization. Going from
local data to live API shows how fragile a feature can be if the front end and backend does not agree on a field name.

I also learned how to use the @SerailName to understand how to keep my code clean while trying to follow API requirements.

---

## One Thing I'm Still Confused About

One thing for me is the best practice for Data Transfer Objects. I have a Quote class for receiving data and a 
QuoteRequest for sending. I want to see what is the best way to manage these too since they are fairly similar
without duplicating anything. Also, if it is better to use one class or keep them separated.


---

## Anything Else *(optional)*

Being assigned a feature was a good challenge. It really gave me a good push to think about how to integrate
a new content into the architecture of the app. It was satisfying to see a new tab added.

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Honest final-status report — what works end-to-end, what's rough, what's tested — plus a specific, genuine "Understood More Deeply" that reflects on the sprint as a whole, not just this week. | Present but vague, or only reports on this week rather than the feature's overall state. | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** same as every other week — I check the link before grading.
