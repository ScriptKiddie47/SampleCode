NodeJS -> Open Source,Cross Platfrom Runtime Environment, Single Threaded,Event Driver & Non Blocking Architecture
Internal Working -> All requests are placed in Event Queue,Event Loop -> Picks up request 1 by 1 from Queue. For non blocking request it quickly processes them. If request is blocking,sends request to review thread pool
Why NodeJS is Single Threaded - Only 1 main thread. Reduces complexity & avoid thread conflicts or deadlocks.
 