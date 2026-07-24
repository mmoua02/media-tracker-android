# Week 10 Reflection

**Name:** Mai Moua
**Date:** 07/23/2026

---

## Commits This Week

**Link:** https://github.com/mmoua02/media-tracker-android/pull/10

---

## Code Review

**Reviewed:** Diego Godinez
**Link to my review:** https://github.com/dgodinez227/media-tracker-android/pull/10#pullrequestreview-4769724536

### What I Looked At

For this review, I focused  on the network logic in DefaultMediaRepository.kt, 
specifically  the search header handling, 404 response checks, the newly added 
addToFavorties placeholder function, and library interaction logic. Additionally, I reviewed 
MediaDetailViewModel.kt to look at the refactor, which transitions to a unified MediaDetailUiState
sealed interface capable of managing details, library status, and reviews all at once.

### What I Noticed

During the review, I noticed a minor spelling typo in DefaultMediaRepository.kt. I pointed it out in case,
my pod mate accidentally made a mistake or if he intended to, just in case that messes with his code.
I also notice that addToLibrary also returns null silently upon a failed response without logging the error.
On more of the architecture side, I saw that the restructuring of the ViewModel nicely takes in the details,
library status, and reviews into one success payload.

### Comments I Left

I left a note pointing out the typo that was mentioned before. I also added a positive comment about
the 404 exception handling but also suggesting that maybe an exception throw or a rest wrapper can be 
added to avoid the silent failures. And then similarly, I also complimented the clean implementation 
of the sealed interface but recommended to safely navigate around the data if an independent network call
was to fail.

---

## One Thing I Understood More Deeply

I understood how to make my data more reactive across different screens. I was stuck on why adding a book
on the Detail Screen was not showing up in my Library. It was because my repository was missing the 
StateFlow. Once that was added in, I can see that every screen that is connected to that flow refreshes 
when a change is made.


---

## One Thing I'm Still Confused About

The logic for rolling back a change is still confusing to me. I understand that a 'backup' of the list 
needs to be saved before changing, but keeping track of the state when multiple things are happening 
at once can get complicated.

---

## Anything Else *(optional)*

It was really satisfying to see the 'Save' button turn red and fill in the heart icon. It's a small
detail but that was really frustrating during the first section of class when I clicked the button, 
actually both buttons and nothing was happening visually.

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
