1. Files read: `bikeshare-poor/src/main/java/bikeshare/Bike.java`, `bikeshare-poor/src/main/java/bikeshare/BikeShare.java`

2. Findings:

| No. | Check | Code location (file:line) | Finding | Severity | Suggested fix |
| --- | --- | --- | --- | --- | --- |
| 1 | Check 1 | `BikeShare.java:18` | Code smell: Station string literals `"Library"`, `"Canteen"`, `"Dorm"` and member type literals are repeated across methods. | Should fix | Extract repeated literals into constants or an enum. |
| 2 | Check 1 | `BikeShare.java:51` | Long / complex method: `processRide` takes 6 parameters and mixes rent search, return fee calculation, console logging, and file saving in nested branches. | Should fix | Decompose `processRide` into distinct, single-purpose helper methods. |
| 3 | Check 2 | `BikeShare.java:51` | Weak method cohesion (logical cohesion): `processRide` uses boolean `isRent` to combine two distinct operations (renting and returning) into one method. | Must fix | Split `processRide` into two separate methods: `rentBike` and `returnBike`. |
| 4 | Check 2 | `Bike.java:16` | Weak method cohesion (logical cohesion): `update` uses boolean `isRent` to perform two opposite state mutations. | Should fix | Replace `update` with dedicated `rent` and `returnTo` methods on `Bike`. |
| 5 | Check 2 | `BikeShare.java:15` | Weak method cohesion (procedural cohesion): `main` sequences station setup, CLI input reading, tokenization, and command dispatching. | Note | Extract the command loop and input handling into separate methods. |
| 6 | Check 2 | `BikeShare.java:9` | Weak class cohesion: `BikeShare` mixes user interface, input parsing, inventory tracking, fee calculations, and CSV persistence in a single class. | Must fix | Separate responsibilities into distinct classes for CLI, service logic, and persistence. |
| 7 | Check 2 | `Bike.java:26` | Weak class cohesion: `Bike.update` modifies the external collection `BikeShare.bikes` directly (`BikeShare.bikes.add(this)`). | Should fix | Remove collection management logic from `Bike` and manage inventory in a repository or service. |
| 8 | Check 2 | `Bike.java:26` | Tight coupling (common coupling): `Bike` and `BikeShare` communicate via shared mutable global state `BikeShare.bikes`. | Must fix | Remove public static shared state and pass dependencies via constructors or parameters. |
| 9 | Check 2 | `BikeShare.java:36` | Tight coupling (control coupling): `main` passes a boolean flag (`true` / `false`) to `processRide` to control internal branching. | Should fix | Call distinct methods for renting and returning instead of passing a control flag. |
| 10 | Check 2 | `BikeShare.java:56` | Tight coupling (control coupling): `processRide` passes `isRent` to `Bike.update` to dictate its internal execution path. | Should fix | Call specific state transition methods on `Bike` directly. |
| 11 | Check 2 | `BikeShare.java:55` | Tight coupling (content coupling): `BikeShare` directly accesses and mutates public fields of `Bike` (`bike.station`, `bike.rentedBy`, `bike.id`, `bike.memberType`). | Must fix | Encapsulate fields in `Bike` as `private` and provide accessor methods. |
| 12 | Check 2 | `Bike.java:7` | Connascence that could be weaker (connascence of convention): `Bike` and `BikeShare` must agree by convention that `1 = student` and `2 = staff`. | Should fix | Replace integer codes with a `MemberType` enum. |
| 13 | Check 2 | `Bike.java:5` | Connascence that could be weaker (connascence of convention): Stations are represented by raw strings without validation or compile-time safety. | Should fix | Introduce a `Station` enum to represent valid stations. |
| 14 | Check 2 | `Bike.java:16` | Connascence that could be weaker (connascence of position): `update` takes adjacent same-typed parameters (`String, String, int, boolean`), risking argument transposition. | Note | Use domain value objects or distinct types to prevent positional errors. |
| 15 | Check 2 | `Bike.java:4` | Non-private fields: `id`, `station`, `rentedBy`, and `memberType` are declared `public`. | Must fix | Make all fields `private` and provide necessary getters and mutators. |
| 16 | Check 2 | `BikeShare.java:10` | Exposed mutable internal state: `public static List<Bike> bikes`, `studentRate`, `staffRate`, and `fileName` are public mutable static variables. | Must fix | Make fields `private` and instance-scoped, exposing only immutable collections or operations. |

3. Number of findings for each check:
- **Check 1 (Code quality):** 2 findings
- **Check 2 (Cohesion, coupling and connascence):** 14 findings
