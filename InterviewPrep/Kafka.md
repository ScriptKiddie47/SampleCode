# Topics, Partitions and Offsets

Kafka Cluster -> Kafka Brokers -> Topics -> Partitions -> Offsets
Source System -> Producer -> Round Robin | Key Based Ordering | Acks Strategy
Consumer -> Consumer Offset | Consumer Groups -> Target Systems

Once the data is written to a partition, it cannot be changed (immutability)
Order is guaranteed only within a partition
Data is assigned to a random partition unless a key is provided.

Kafka Common Serializers: String, Int, Float, Avro
Zookeeper manages brokers — not required from Kafka 3.3+ (KRaft mode). Fully removed in v4.
Zookeeper does NOT store consumer offsets with Kafka > v0.10
Kafka KRaft - KIP-500 - Replaced Zookeeper with a built-in Raft-based metadata quorum

`Acks Strategy` (Producer durability guarantee):
- `acks=0` - Producer does not wait for any acknowledgement. Fastest, but data can be lost.
- `acks=1` - Producer waits for the leader broker to acknowledge. Data lost if leader crashes before replication.
- `acks=all` - Producer waits for the leader + all In-Sync Replicas (ISR) to acknowledge. Strongest guarantee, no data loss.

Producer Writes to Topic:
- `Round Robin` - No key provided; messages distributed evenly across all partitions
- `Sticky Partition` - No key provided; batches multiple messages to the same partition before switching. Performance improvement over pure round-robin (fewer, larger batches)
- `Key Based` - Same key always goes to the same partition, guaranteeing ordering per key

Consumer - Define `Consumer Groups` - `Partition is assigned to Consumers` - Each partition is consumed by exactly one consumer within a group
Auto Commit - When we call the `poll()` method & `auto.commit.interval.ms` has elapsed. Commits the last polled offset — if the consumer crashes mid-batch, those messages are reprocessed (at-least-once delivery)

