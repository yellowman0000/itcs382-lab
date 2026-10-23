# Lab Report, Code Quality and Design Quality

Repository URL: https://github.com/yellowman0000/itcs382-lab

## Tools

| Item | Version or name |
| --- | --- |
| JDK | java 24.0.2 2025-07-15 |
| Maven | Apache Maven 3.10.0 |
| IDE | Antigravity IDE |
| SonarQube for IDE | 6.1.1|
| AI tool and exact model | Gemini 4 Argon High|

## Task 1. SonarQube on the starter code

Commit of the starter, before any change: `5639620`

Table 1. SonarQube issues in the starter code

| No. | Rule | File:line | Message |
| --- | --- | --- | --- |
| 1 | Standard outputs should not be used directly to log anything (java:S106)| Main.java:9 | Replace this use of System.out by a logger.|
| 2 | Standard outputs should not be used directly to log anything (java:S106)| Main.java:15 | Replace this use of System.out by a logger.|
| 3 | Standard outputs should not be used directly to log anything (java:S106)| Main.java:17 | Replace this use of System.out by a logger.|
| 4 | Standard outputs should not be used directly to log anything (java:S106)| Main.java:22 | Replace this use of System.out by a logger.|
| 5 | "java.time" classes should be used for dates and times (java:S2143)| ParcelService.java:1 | Use the "java.time" API for date and time.|
| 6 | Unnecessary imports should be removed (java:S1128)| ParcelService.java:6 | Remove this unused import 'java.util.Date'.|
| 7 | Unused "private" fields should be removed (java:S1068)| ParcelService.java:12 | Remove this unused "maxParcels" private field.|
| 8 | Standard outputs should not be used directly to log anything (java:S106)| ParcelService.java:30 | Replace this use of System.out by a logger.|
| 9 | Strings and Boxed types should be compared using "equals()" (java:S4973) | ParcelService.java:42 | Strings and Boxed types should be compared using "equals()". |
| 10 | Standard outputs should not be used directly to log anything (java:S106) | ParcelService.java:67 | Replace this use of System.out by a logger. |
| 11 | Standard outputs should not be used directly to log anything (java:S106) | ParcelService.java:69 | Replace this use of System.out by a logger. |
| 12 | Resources should be closed (java:S2095) | ParcelService.java:76 | Use try-with-resources or close this "FileWriter" in a "finally" clause. |
| 13 | Nested blocks of code should not be left empty (java:S108) | ParcelService.java:80 | Remove this block of codem fill it in, or add a comment explaining why it is empty. |

## Task 2. AI review of the starter code

| Item | Value |
| --- | --- |
| Date | 20/10/2026 |
| AI tool and exact model | Gemini 4 Argon High |
| Commit reviewed | `5639620` |
| How the AI read the files (attached the files, opened the folder, or pasted the files) | Opened the folder |
| Output file in the reviews folder | `reviews/parcelpoint-review.md` |

Table 2. All findings on the starter code

| No. | File:line | Finding | Found by (SonarQube, AI or both) | SonarQube rule or course term | Correct? (yes or no, and why) |
| --- | --- | --- | --- | --- | --- |
| 1 | Main.java:9 | Replace `System.out` with a logger | SonarQube | java:S106 | No, `Main` is an interactive CLI entry point where `System.out` is expected for user prompts. |
| 2 | Main.java:15 | Replace `System.out` with a logger | SonarQube | java:S106 | No, printing command results to standard output is the intended CLI behavior in `Main`. |
| 3 | Main.java:17 | Replace `System.out` with a logger | SonarQube | java:S106 | No, printing the pickup fee to the console is required by the CLI interface in `Main`. |
| 4 | Main.java:22 | Replace `System.out` with a logger | SonarQube | java:S106 | No, displaying free lockers on the console is expected CLI output in `Main`. |
| 5 | ParcelService.java:1 | Use `java.time` API instead of `java.util.Date` | SonarQube | java:S2143 | Yes, `java.util.Date` is legacy (though the import at line 6 is completely unused). |
| 6 | ParcelService.java:6 | Unused import `java.util.Date` | both | java:S1128 / Code smell (unused import) | Yes, `Date` is never referenced anywhere in `ParcelService.java`. |
| 7 | ParcelService.java:12 | Unused private field `maxParcels` | both | java:S1068 / Code smell (unused field) | Yes, `maxParcels` is declared and initialized to `100` but never read anywhere in the class. |
| 8 | ParcelService.java:30 | Replace `System.out` with a logger | SonarQube | java:S106 | Yes, `ParcelService` is a domain service and should not print SMS messages directly to `System.out`. |
| 9 | ParcelService.java:42 | `String` objects `parcel.getCode()` and `code` compared using `==` instead of `equals()` | both | java:S4973 / Probable bug (`==` vs `equals()`) | Yes, `code` comes from `Scanner.nextLine().split(" ")`, which creates a new `String` instance that fails `==` reference comparison. |
| 10 | ParcelService.java:67 | Replace `System.out` with a logger | SonarQube | java:S106 | Yes, domain service methods should return data rather than writing directly to `System.out`. |
| 11 | ParcelService.java:69 | Replace `System.out` with a logger | SonarQube | java:S106 | Yes, domain service methods should not be coupled directly to standard output. |
| 12 | ParcelService.java:76 | `FileWriter` opened for `"parcels.csv"` is never closed | both | java:S2095 / Probable bug (unclosed resource) | Yes, `new FileWriter("parcels.csv")` is not in a `try`-with-resources block and `close()` is never called, leaving output unflushed. |
| 13 | ParcelService.java:80 | Empty `catch (IOException e)` block ignores exceptions | both | java:S108 / Code smell (empty block) | Yes, lines 80–81 contain an empty `catch` block that silently swallows any `IOException`. |
| 14 | Main.java:8 | `Scanner(System.in)` is never closed before `main` exits | AI | Probable bug (unclosed resource) | No (or minor nuisance), closing a `Scanner` wrapping `System.in` closes the standard input stream for the entire JVM, which is why SonarQube exempts `System.in`. |
| 15 | ParcelService.java:28 | Status string literal `"STORED"` is repeated at lines 28 and 68 | AI | Code smell (repeated string literals) | Yes, `"STORED"` appears as a raw string literal in both `storeParcel` (line 28) and `printParcels` (line 68). |
| 16 | ParcelService.java:24 | `storeParcel` combines locker allocation, status update, SMS console output, and CSV persistence | AI | Procedural cohesion | Yes, these operations only share execution order in the storage workflow rather than a single functional responsibility. |
| 17 | ParcelService.java:39 | `pickUp` combines freeing a locker, updating status, saving to CSV, and computing a fee | AI | Procedural cohesion | Yes, it sequences distinct responsibilities (state mutation, file I/O, and fee calculation) in one method. |
| 18 | ParcelService.java:64 | `printParcels(boolean forStaff)` uses a flag to choose between two different output behaviors | AI | Logical cohesion | Yes, `if (forStaff) ... else if (...)` selects between two different reporting formats based on a control flag. |
| 19 | Main.java:6 | `main` combines setup, input reading, string splitting, and multi-branch command dispatch | AI | Procedural cohesion | Yes, `main` sequences initialization and a command loop handling five different operations in one method. |
| 20 | ParcelService.java:9 | `ParcelService` mixes locker management, pricing (`fee`), console printing, and CSV I/O, and `fee` (line 52) uses none of `ParcelService`'s fields | AI | Weak class cohesion | Yes, `ParcelService` has multiple unrelated responsibilities and `fee(Parcel, int)` only touches its parameters. |
| 21 | ParcelService.java:28 | `ParcelService` directly reads and modifies `Parcel.status` at lines 28, 44, 67, 68, and 78 | AI | Content coupling | Yes, `ParcelService` bypasses encapsulation and mutates `parcel.status` directly. |
| 22 | Main.java:19 | `Main` passes boolean flags (`false` at line 19, `true` at line 21) to `ParcelService.printParcels(boolean)` | AI | Control coupling | Yes, the caller passes a control flag (`forStaff`) that dictates the internal branch taken by `printParcels`. |
| 23 | ParcelService.java:52 | `fee(Parcel, int)` accepts the whole `Parcel` when it only reads `parcel.getSize()`, and `storeParcel` returns `Locker` to `Main.java:14` when `Main` only reads `locker.getNumber()` | AI | Stamp coupling | Yes, composite domain objects are passed/returned where only a single field (`size` or `number`) is used. |
| 24 | Main.java:22 | `Main` reaches through `service.getLockers()` to call `Locker::isFree` and depends on `ParcelService`, `Parcel`, and `Locker` | AI | Strong coupling (Law of Demeter) | Yes, `service.getLockers().stream().filter(Locker::isFree).count()` chains through `ParcelService`'s internal list to `Locker`. |
| 25 | Parcel.java:6 | `Parcel`, `Locker`, `Main`, and `ParcelService` must agree on `int` codes `1`, `2`, `3` for small, medium, and large sizes | AI | Connascence of convention | Yes, the meaning of `1 = small, 2 = medium, 3 = large` is shared only by convention and comments across classes. |
| 26 | Parcel.java:13 | `Parcel` and `ParcelService` must agree on exact string literals `"NEW"`, `"STORED"`, and `"PICKED_UP"` for parcel states | AI | Connascence of convention | Yes, typos in any of these raw strings would compile cleanly and fail only at runtime. |
| 27 | Parcel.java:9 | `Parcel(String code, String studentId, int size)` and `Locker(int number, int size)` take adjacent same-typed arguments | AI | Connascence of position | Yes, callers at `Main.java:13` and `ParcelService.java:15–17` must pass same-typed arguments (`String, String` or `int, int`) in the exact positional order. |
| 28 | Parcel.java:7 | `public String status;` is not `private` | AI | Non-private field (encapsulation) | Yes, line 7 declares `status` as `public`, allowing any class to overwrite it arbitrarily. |
| 29 | ParcelService.java:20 | `getLockers()` returns the mutable internal `List<Locker>` directly | AI | Exposed internal collection (encapsulation) | Yes, callers of `getLockers()` receive a direct reference to `private List<Locker> lockers` and can mutate it. |

2.1 Name two problems that only the AI review found.

1. **Content coupling and non-private field (`Parcel.java:7` and `ParcelService.java:28, 44, 67, 68, 78`):** `Parcel.status` is declared `public`, and `ParcelService` directly reads and overwrites `parcel.status` (`"STORED"`, `"PICKED_UP"`) instead of calling methods on `Parcel`.
2. **Control coupling and logical cohesion (`Main.java:19, 21` and `ParcelService.java:64`):** `Main` passes a boolean control flag (`false` or `true`) to `ParcelService.printParcels(boolean forStaff)` to select between printing waiting parcels for students and printing all parcel details for staff. *(Other AI-only design findings include exposing the mutable `lockers` list in `ParcelService.getLockers()` at `ParcelService.java:20`, stamp coupling in `ParcelService.fee(Parcel, int)` at `ParcelService.java:52`, and connascence of convention for integer sizes `1..3` and status strings.)*

2.2 Name one problem that only SonarQube found, or write none.

SonarQube alone reported **`java:S106` ("Standard outputs should not be used directly to log anything")** at `Main.java:9, 15, 17, 22` and `ParcelService.java:30, 67, 69`, as well as **`java:S2143` ("`java.time` classes should be used for dates and times")** at `ParcelService.java:1` (triggered by the unused `import java.util.Date;` on line 6).

2.3 Name one AI finding that was wrong or too vague, and explain why. If every finding was right, explain how you checked them.

Every AI finding pointed to an exact file and line that matched the actual source code when checked against `Locker.java`, `Main.java`, `Parcel.java`, and `ParcelService.java`. However, **AI finding #3 (`Main.java:8`, unclosed `Scanner(System.in)`)** is overly strict in practice: although `Scanner` implements `AutoCloseable`, closing a `Scanner` that wraps `System.in` also closes the JVM's standard input stream, which is why SonarQube's resource-leak rule (`java:S2095`) intentionally ignores `Scanner` instances backed by `System.in`. All other findings were verified by inspecting the referenced lines and testing the runtime behavior (for example, confirming that `parcel.getCode() == code` at `ParcelService.java:42` evaluates to `false` during `pickup` and that `new FileWriter("parcels.csv")` at `ParcelService.java:76` leaves `parcels.csv` empty because the stream is never flushed or closed).

## Task 3. Fix and check

Table 3. What you did with each SonarQube issue in Table 1

| No. in Table 1 | Fixed or kept | Commit, or the reason for keeping it |
| --- | --- | --- |
| 1 | Kept | Printing the command menu in Main is the expected console output of an interactive CLI program. |
| 2 | Kept | Printing the locker assignment in Main is the expected console output of an interactive CLI program. |
| 3 | Kept | Printing the pickup fee in Main is the expected console output of an interactive CLI program. |
| 4 | Kept | Printing the free lockers count in Main is the expected console output of an interactive CLI program. |
| 5 | Fixed | `a1dd147` |
| 6 | Fixed | `a1dd147` |
| 7 | Fixed | `57361c4` |
| 8 | Kept | Printing the SMS notification to standard output simulates sending an SMS alert in this console program. |
| 9 | Fixed | `e7d54c0` |
| 10 | Kept | Printing parcel records to standard output is the required console output for the staff command. |
| 11 | Kept | Printing waiting parcels to standard output is the required console output for the list command. |
| 12 | Fixed | `457f049` |
| 13 | Fixed | `4c43809` |

Number of SonarQube issues that remain after your fixes: 7

The two design problems that you fixed

| Design problem (course term) | What you changed | Commit |
| --- | --- | --- |
| Content coupling | Made `Parcel.status` private and added `getStatus()` and `setStatus()`, removing direct field mutation from `ParcelService`. | `bed510a` |
| Weak class cohesion | Moved storage fee calculation logic to `Parcel.fee(int days)` so `Parcel` calculates its fee using its own size, and delegated from `ParcelService`. | `9fd7191` |

Output of the program after your fixes, for the commands `store P1 6501 2`, `pickup P1 5` and `staff`, and the content of parcels.csv:

```text
Commands: store <code> <studentId> <size 1-3>, pickup <code> <days>, list, staff, quit
SMS to 6501: parcel P1 is in locker 2
Locker 2
Fee: 30.0
P1 6501 PICKED_UP
Free lockers: 3

parcels.csv:
P1,PICKED_UP
```

## Task 4. AI-generated poor and good designs

| Item | bikeshare-poor | bikeshare-good |
| --- | --- | --- |
| Date | 23/10/2026 | 23/10/2026 |
| AI tool and exact model | Gemini 4 Argon | Gemini 4 Argon |
| Prompt (B or C as given, or your changed prompt in full) | Prompt B as given | Prompt C as given |
| Builds with mvn package (yes or no) | yes | yes |
| Number of classes, interfaces, enums and records | 2 (2 classes) | 8 (5 classes, 1 interface, 2 enums) |
| SonarQube issues, total | 16 | 7 |
| SonarQube issues, by rule | java:S106: 8, java:S1104: 4, java:S1444: 4 | java:S106: 7 |
| AI review findings in Check 1 | 2 | 2 |
| AI review findings in Check 2 | 14 | 2 |
| Review output file in the reviews folder | `reviews/bikeshare-poor-review.md` | `reviews/bikeshare-good-review.md` |

4.1 Did SonarQube report any cohesion or coupling problem in bikeshare-poor? Quote the rule and the message if it did.

No. SonarQube did not report any cohesion or coupling problems in `bikeshare-poor`. SonarQube reported only implementation-level code smells and modifier practices, such as `java:S106` (*"Replace this use of System.out by a logger"*), `java:S1104` (*"Class variable fields should not have public accessibility"*), and `java:S1444` (*"Public static fields should always be constant, make them 'final'"*). It did not analyze architectural design quality such as logical/procedural cohesion, common coupling through shared static state, control coupling via boolean flags, or connascence of convention.

4.2 Did the AI review tell the two versions apart? Give one finding for bikeshare-poor that does not appear for bikeshare-good.

Yes. The AI review clearly distinguished between the two designs, identifying 14 cohesion, coupling, and connascence issues in `bikeshare-poor` and only 2 minor notes in `bikeshare-good`.
One finding for `bikeshare-poor` that does not appear for `bikeshare-good` is **control coupling and logical cohesion at `BikeShare.java:51` (`processRide`) and `Bike.java:16` (`update`)**, where a boolean flag `isRent` is passed to switch between two mutually exclusive behaviors (renting and returning). Another prominent finding unique to `bikeshare-poor` is **common coupling at `Bike.java:26` and `BikeShare.java:10`**, where `Bike` directly mutates the public static list `BikeShare.bikes`.

4.3 Which version would you rather extend with a new member type, such as a visitor who pays 4 baht per minute, and why?

I would much rather extend **`bikeshare-good`**.
In `bikeshare-good`, member types and rates are encapsulated in the `MemberType` enum (`MemberType.java`). Adding a visitor paying 4 baht per minute requires adding only a single line to `MemberType`:
```java
VISITOR(4);
```
Because fee calculation is delegated to `MemberType.calculateFee(int minutes)` and token parsing is handled by `MemberType.fromToken`, no modifications are needed in `Bike`, `BikeShareService`, `RideReceipt`, `Station`, `CsvRideRepository`, or `Main` (honoring the Open-Closed Principle).

In contrast, extending `bikeshare-poor` requires making changes scattered across multiple classes due to connascence of convention and weak cohesion:
1. Adding a new rate variable in `BikeShare.java` (`public static int visitorRate = 4;`).
2. Updating the integer convention comment in `Bike.java` (`// 1 = student, 2 = staff, 3 = visitor`).
3. Modifying command token parsing in `BikeShare.main` (line 35) to handle the `"visitor"` string and map it to code `3`.
4. Modifying `BikeShare.calculateFee` (lines 78–82) with an additional `else if (bike.memberType == 3)` branch.
This multi-class ripple effect increases coupling and makes the codebase error-prone to maintain.

## Task 5. Reflection

5.1 What does SonarQube check, and what does it not check? Answer with examples from this lab.

5.2 When should you not trust the AI review? Give an example from this lab.

5.3 What will you do differently in Project Phase 2 after this lab?

## AI usage declaration

| AI tool and exact model | Purpose | Files or parts that it produced or changed | How you checked its output |
| --- | --- | --- | --- |
| | | | |
