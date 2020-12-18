package io.github.aluria.kingdoms.models.invite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Data
@EqualsAndHashCode(of = "id")
public class Invite {

    private final UUID id;
    private final UUID kingdomId;

    private final String invitedName;
    private final String inviterName;

    private final Timestamp creationDate = Timestamp.from(Instant.now());
    private final Timestamp expirationDate = Timestamp.from(Instant.ofEpochMilli(System.currentTimeMillis() + 300000));

}
