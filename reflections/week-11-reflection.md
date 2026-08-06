# Week 11 Reflection — Bonus Feature Sprint (Week 1 of 2)

**Name:** Mai Moua

**Date:** 07/30/2026

**My assigned bonus feature:** Quotes

---

## Commits This Week

**Link:** https://github.com/mmoua02/media-tracker-android/pull/11

---

## Code Review

<!-- Code review continues as normal — same pod rotation, regardless of which bonus feature you or your pod mate are building. -->

**Reviewed:** Ryan Burke

**Link to my review:** https://github.com/oppenrhymer/media-tracker-android/pull/11#pullrequestreview-4824930107

### What I Looked At

For this review, I looked at the code changes Ryan implemented for his bonus feature. I specifically
looked closer at the WriteReviewScreen.kt to see how the user inputs, buttons, and submission actions 
were handled. I also looked at the WriteReviewViewModel.kt, and saw the management setup for ratings and reviews.
And then lastly, I looked at the MediaDetailScreen.kt to review the logic that was used for displaying the 
review cards on the media detail page.

### What I Noticed

I noticed that the PR implements pretty clean and a reactive state management within the ViewModel. This 
keeps the UI neat and responsive to the data that gets updates. I also noticed that the user interface
has a solid layout for calculating time differences, but there are currently temporary elements in place.

### Comments I Left

With what I noticed, I left comments on there noting the state flow and UI's cleanliness and responsiveness. 
I also added a comment where I currently see a hardcoded/temp element, to suggestion maybe having it disabled until
a rating is selected. For the time calculation, I also left a comment there because I think it was a really clean apporach.

---

## Bonus Feature Progress

<!-- This is the most important section this week. Be concrete: which endpoint(s) did you wire?
     What's actually showing on screen with real data? What's still stubbed or fake?
     "I worked on my bonus feature" is not an answer. "I got POST /quotes working from Media Detail
     and quotes show up in a list on my profile, but I haven't wired edit or delete yet" is. -->

**What's working:**
The "+Add Quote" point on the MediaDetailScreen is functional and opens up a new AddQuoteDialog. 
The dialog handles the required text, which I set to a max of 500, the page number as optional, and the privacy toggle.
I implemented the Quote Model, QuoteRequest, and a QuoteRepository.
The QuoteApiService is wired to the RetroFitInstance with endpoints for GET and POST.

**What's still stubbed, fake, or not started:**
While the POST logic is wired into the code, it is currently failing to save to the backend because the app
is not providing a valid token from the login.
The Login and Register screens are currently not wired to a live API.

**What I'm blocked on, if anything:**

I am currently blocked on the 401 error. I tried updating my local.properties with the apiClientId 
provided, but the server is still rejecting my quotes.

---

## One Thing I Understood More Deeply

I realized how much goes into just adding one new feature. I can't just create a button for it, I have
to create a Model for the data, a Repository to handle all the logics, and a ViewModel to manage the screen.

---

## One Thing I'm Still Confused About

I am stuck on why I am getting the 401 error. Even though my credentials were updated, the server is still rejecting
my requests to save quotes. I think it may just be a mistake on my end of not implementing the token
to the backend sooner and have it recognize me.

---

## Anything Else *(optional)*

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Concrete progress report (what's wired, what's not) plus specific, honest "Understood More Deeply" and "Still Confused" sections. | Present but vague — "I worked on my feature" with no specifics on what's actually working. | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match.
