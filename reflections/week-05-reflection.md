# Week 5 Reflection

**Name:** Mai Moua
**Date:** 06/18/2026

---

## Commits This Week

**Link:** https://github.com/mmoua02/media-tracker-android/pull/5

---

## Code Review

**Reviewed:** Diego Godinez
**Link to my review:** https://github.com/dgodinez227/media-tracker-android/pull/5#pullrequestreview-4529796140

### What I Looked At

I looked through Diego's integration of the registration API. I looked at the APIConstants.kt file 
to review the configuration used for managing the API endpoints and credentials. I also looked at the
cleanups of the Snackbar implementation in the RegisterScreen.kt.

### What I Noticed

I noticed that in the ApiConstants.kt, the CLIENT_ID and CLIENT_SECRET values were defined as "val" 
instead of "const val". Since they are fixed values that doesn't change, using "const val" might be 
the better practice. This will also keep it more efficient and consistent with the "BASE_URL".

### Comments I Left

I suggested changing "val" to "const val" in the ApiConstants.kt file to get in the hang of best practices.
I also gave a suggestion in refactoring the RegisterUiState sealed class so that error messages can 
be exclusive to the Error state.
And lastly, I made positive comments about how much more readable the codes are now that some things 
have been simplified or removed previous unused blocks.

---

## One Thing I Understood More Deeply

I am starting to understand why we use a Repository. I thought that the ViewModel is where all the codes
would go, but I see now that it is special in its ways. By using the Repository, it is acting like a 
middleman so that the ViewModel doesn't have to deal with all the mess. This is showing me how the 
UI layer comes together.

---

## One Thing I'm Still Confused About

I did not get to the API tokens. I am still trying to figure out and understand the best way to 
handle them. I believe I currently have it hard coded, which we shouldn't really be doing.

---

## Anything Else *(optional)*


---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
