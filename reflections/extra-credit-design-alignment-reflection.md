# Extra Credit Reflection — Design Alignment

**Name:** Mai Moua
**Date:** 07/02/2026

---

## The Audit

*Before touching any code, compare your running app to the wireframes screen by screen. List what you found — be specific about which screen, which component, and what was different. "The colors were off" is not specific. "The active chip on the Search screen was using amber instead of primary container (#E0E0FF)" is specific.*

*List at least five concrete differences you found:*

1. My buttons look too square. In the wireframe, the buttons have a more rounded corner. All my buttons should be changed to RoundedCornerShape.
2. The search bar is not the same shape, more rectangular than a pill shape. I will need to implement RoundedCornerShape(28.dp), while regular OutlineTextFields only need 8.dp
3. Comparing my page titles to the wireframe, it looks like mine are lighter. The wireframe is more bold. The weight will need to be updated to Bold (700).
4. Comparing the status badges, I looked at the color table provided and what I have. I will need to double-check and update the colors to match.
5. I need to check if the active indicator pill behind the icon matches the PrimaryContainer (#E0E0FF).

---

## What You Changed

*Walk through the changes you made. For each area of the design system, describe what the code looked like before and what you changed it to. Reference specific files and Composables.*

### Color System

Before this, I was using hardcoded color values directly in my component modifiers, which made the app
look inconsistent and hard to update. I update Color.kt to include the specific palette, I then wired them into
the MaterialTheme by updating Theme.kt. This should allow me to use MaterialTheme.colorScheme.primary or surface throughout the app instead of raw codes.

### Typography

My text styles were largely using default MaterialTheme typography, but I had instances where font weights were hardcoded locally on the Text composables. 
I updated the Type.kt to define clear headline, title, and label styles. By centralizing these, it should be applied through every screen.

### Buttons

I had standard buttons implementations that ignored the required corner radius and specific container colors.
I created an AppButton wrapper to enforce the 20.dp corner radius and use the correct primary/onPrimary color tokens 
from the colorScheme, to make sure that they match the wireframe specs.

### Text Fields

My text fields were previously just default OutlinedTextField components. I updated them to enforce the 8.dp 
shape and custom colors. I moved these into a reusable AppTextField component to ensure that the label colors, 
cursor colors, and focused/unfocused border states behave consistently across the login and edit profile screens.

### Other Components

Filter Chips: I created AppFilterChip to handle the 8.dp shape and specific container color logic (using primaryContainer for selected states).

Status Badges: I built a StatusBadge component to handle the semantic colors (Want To, In Progress, Finished) which were previously just simple text labels.

Cards: I updated all Card components to use CardDefaults.cardElevation(defaultElevation = 2.dp) and a 12.dp shape.

Bottom Navigation: I configured NavigationBarItem to use the primaryContainer for the indicator pill and mapped the active/inactive colors to the primary and onSurfaceVariant tokens.

---

## What Was Hard

The most challenging part was understanding how MaterialTheme handles component defaults vs. explicit overrides. 
I tried to override everything in the Modifier, but I realized that was fighting the system rather than using it. 
Specifically, the FilterChip was tricky. I initially struggled to get the border and container colors to switch correctly between active and inactive states. 
I had to learn how FilterChipDefaults.filterChipColors works in conjunction with BorderStroke to ensure the component actually respected the design system tokens rather than 
just defaulting to the Material 3 defaults.

---

## What You Understand Now

I now grasp that MaterialTheme is essentially a "design contract." By defining colorScheme and typography in the theme, you provide a single source of truth for the entire app. 
I understand that instead of hardcoding values, I should be looking at the MaterialTheme.colorScheme and MaterialTheme.typography properties. 
I can now explain to a pod mate that if you want a consistent app, you don't style individual components, 
you style the Theme and create wrapper components (like AppCard or AppButton) that consume those theme values automatically.

---

## Self-Assessment

*Look at the rubric (`extra-credit-design-alignment-rubric.md`) and estimate your own score for each section. Be honest — this does not affect your grade, but it shows me whether you read the rubric carefully.*

| Section | Possible | My Estimate |
|:---|:---:|:-----------:|
| Color System | 13 |     10      |
| Typography | 5 |      3      |
| Component Styling | 15 |     10      |
| Navigation & Cards | 5 |      3      |
| Reflection | 12 |     12      |
| **Total** | **50** |     38      |

*One thing I think I did well:*

I think I did well in moving to a centralized component library. This not only fixed the immediate design gaps but also significantly reduced the amount of codes on my screen.

*One thing I know I left incomplete or could have done better:*

I could have spent more time on refining the interaction states for the navigation bar. Like customizing certain effect, but I focused on prioritizing getting the color tokens working correctly first.
