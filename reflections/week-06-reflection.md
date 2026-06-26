# Week 6 Reflection

**Name:** Mai Moua
**Date:** 06/25/2026

---

## Commits This Week

**Link:** https://github.com/mmoua02/media-tracker-android/pull/6

---

## Code Review

**Reviewed:** Ryan Burke
**Link to my review:** https://github.com/oppenrhymer/media-tracker-android/pull/6#pullrequestreview-4576353834

### What I Looked At

I reviewed Ryan's pull request, which was about adding a new search screen and the corresponding
API integration. I mainly looked through the repository and the navigation layers, specifically more into
the MediaResultsRepository to see how the data will get fetched, how the user session is managed 
in the DefaultSessionRepository, along with how the navigations are handled in the NavGraph.

### What I Noticed

I noticed that the PR is pretty well-structured, with how it separates between data and UI components.
I was going to raise a potential issue where the filter was ties to the fakeSearchResult but I realized 
that that was just there for the time being in comparison the live data.

### Comments I Left

I praised the setup of the DataStore and the use of json to handle the user profile serialization.
I also commented on the clear structure of the OkHttpClient and the implementation of the Authorization header.
I noted that he has a clean approach in the NavGraph with how the query is passed through the navigation string.
Lastly, i noted the organization and color coding of the media result cards in the SearchComponent.

---

## One Thing I Understood More Deeply

I think the arguments that were passed in the NavGraph is starting to click for me. I was unsure how
update a screen based on a search string, but seeing how the route and the arguments go together
made more sense.

---

## One Thing I'm Still Confused About

I am not 100% sure how to handle a null or empty token case within the search() function to ensure the app 
does not crash or hangs if the user's session expires. This is something that I would like to practice more
on.

---

## Anything Else *(optional)*

<!-- Did you help a pod mate work through something? Did you discover something cool or frustrating?
     Did something from a previous week finally click? This is a good place to put it. -->

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
