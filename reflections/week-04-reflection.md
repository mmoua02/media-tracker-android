# Week 4 Reflection

**Name:** Mai Moua
**Date:** 06/11/2026

---

## Commits This Week

**Link:** https://github.com/mmoua02/media-tracker-android/pull/4

---

## Code Review

**Reviewed:** Ryan Burke

**Link to my review:** https://github.com/oppenrhymer/media-tracker-android/pull/4#pullrequestreview-4481946848

### What I Looked At

I reviewed the pull request for the registration screen and ViewModel. I mainly focused on the 
validation logic. I checked the register screen to see how the buttons may interact and in the ViewModel
I looked at how the form values and errors were managed.


### What I Noticed

I did notice that there was a large block of code commented out, I understand that he may want to save
the work or code to go back on, but that can make the file a little clustered and hard to read. 

Everything else looks great with him having a RegisterScreen and a RegisterViewModel.

### Comments I Left

I left a comment suggesting that the commented out block can be removed to clean up the file.
I also noted that the error message implementation looks great, but it did get me wondering 
if the message stays on the screen. It is something I should look into on my end that if the 
user fixes or makes changes the error message should clear out.
And then I just left a positive note at the end on the improvement of the structure.

---

## One Thing I Understood More Deeply

One thing I understood more deeply this week is the use of MutableStateFlow to manage the UI
state in a ViewModel. By keeping it private and showing it as asStateFlow(), I learned how to enforce 
one way information to prevent errors and changes on the data directly.

---

## One Thing I'm Still Confused About

One thing I am still confused about that me and my team questioned for a bit was how to 
implement themes and colors across. I think we understand the basics of using it but just don't 
know how to apply it without all the hardcoding.

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
