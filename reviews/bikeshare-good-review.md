1. Files read: `bikeshare-good/src/main/java/bikeshare/Bike.java`, `bikeshare-good/src/main/java/bikeshare/BikeShareService.java`, `bikeshare-good/src/main/java/bikeshare/CsvRideRepository.java`, `bikeshare-good/src/main/java/bikeshare/Main.java`, `bikeshare-good/src/main/java/bikeshare/MemberType.java`, `bikeshare-good/src/main/java/bikeshare/RideReceipt.java`, `bikeshare-good/src/main/java/bikeshare/RideRepository.java`, `bikeshare-good/src/main/java/bikeshare/Station.java`

2. Findings:

| No. | Check | Code location (file:line) | Finding | Severity | Suggested fix |
| --- | --- | --- | --- | --- | --- |
| 1 | Check 1 | `Main.java:20` | Code smell: Standard output `System.out` is used directly for command prompts and messages across `Main.java`. | Note | Replace direct `System.out` calls with a dedicated logging or UI abstraction if required by production standards. |
| 2 | Check 1 | `CsvRideRepository.java:18` | Code smell: `IOException` is rethrown as an unchecked `UncheckedIOException` rather than caught and handled with domain recovery. | Note | Add explicit error recovery or user notification in caller for failed file persistence. |
| 3 | Check 2 | `Main.java:19` | Weak method cohesion (procedural cohesion): `run` sequences input reading, command line trimming, and routing to `handleCommand`. | Note | Keep loop minimal or separate terminal I/O from dispatching. |
| 4 | Check 2 | `Main.java:50` | Coupling: `Main` directly coordinates `BikeShareService`, `RideReceipt`, `MemberType`, and `Station`. | Note | Encapsulate presentation mapping within a dedicated presenter or facade to further decouple `Main`. |

*(Sub-checks with no occurrences: unclosed resources — not found (all managed via try-with-resources); == used instead of equals — not found; unused imports/fields — not found; empty blocks — not found; methods too long/complex — not found; logical/temporal/coincidental cohesion — not found; weak class cohesion — not found; common/content/control coupling — not found; connascence of convention/position/algorithm — not found; public fields or exposed collections — not found.)*

3. Number of findings for each check:
- **Check 1 (Code quality):** 2 findings
- **Check 2 (Cohesion, coupling and connascence):** 2 findings
