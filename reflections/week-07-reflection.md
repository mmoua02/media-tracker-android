# Week 7 Reflection

**Name:** Mai Moua
**Date:** 07/02/2026

---

## Commits This Week

**Link:** https://github.com/mmoua02/media-tracker-android/pull/8

---

## Code Review

<!-- Every week you leave a review on a pod mate's pull request. Fill in both parts below.
     Part 1 is the link — I will verify the review exists on GitHub.
     Part 2 is your written assessment — what you actually looked at and what you found. -->

**Reviewed:** Diego Godinez
**Link to my review:** https://github.com/dgodinez227/media-tracker-android/pull/7#pullrequestreview-4622417916

### What I Looked At

I specifically focused on the MediaDetailScreen.kt file. The application started as hardcoding data,
but it started to move away from that to integrate data driven navigations. I looked at it to ensure 
the detail screen could correctly receive and display information based on the mediaId that was passed 
through, rather than defaulting to a placeholder. 

### What I Noticed

I noticed the by replacing the hardcoded mediaId with a value that was extracted, the navigation flow is more properly integrated with the 
rest of the application. The UI is definitely becoming more polished especially with the layout modifiers.

### Comments I Left

I left positive comments praising the clean structure on the file and the use of navigation patterns, which 
made the code easier to follow. I also commented on the fix for the mediaId as it resolved the issue of hardcoding.

---

## One Thing I Understood More Deeply

I struggled to understand how to bridge the gap between the wireframe and the actual UI code, but after working on
today's project, I saw how to use Column as a flexible way to build complex screens without needing to rely on a 
complex structure.

---

## One Thing I'm Still Confused About

I am still a bit confuse on how to handle image loading. I believe Benjamin briefly went over it, but I have not had the chance to 
try an implement that on my project. Since I hardcoded my file to get the layout working, I am unsure of how to gracefully transition
from hardcoding to real loaded-images.

---

## Anything Else *(optional)*

I really enjoyed the pace of today's class, or maybe it was just what we were specifically working on. I found it enjoyable and the 
perfect challenge for me. I would say it was also great because me and my pod referenced the frame through other Screens that we have worked on.

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
