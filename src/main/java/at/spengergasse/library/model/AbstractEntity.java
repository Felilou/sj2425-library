package at.spengergasse.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@SuperBuilder
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    @PrePersist
    private void prePersist() {
        setUUID(UUID.randomUUID());
        afterUUIDSet();
    }

    public void afterUUIDSet(){};

    public void setUUID(UUID uuid) {
        if(this.uuid == null) {
            this.uuid = UUID.randomUUID();
            if(uuid != null) {
                this.uuid = uuid;
            }
        } else {
            throw new IllegalStateException("UUID is already set");
        }
    }

    @PreRemove
    private void removeUUID() {
        this.uuid = null;
        afterRemoveUUID();
    }

    public void afterRemoveUUID(){};

    @JsonIgnore
    public Long getId() {
        return id;
    }

    protected abstract boolean individualEquals(Object other);

    protected abstract int individualHashCode();

    @Transient
    @JsonIgnore
    public boolean isNew() {
        return null == getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this.isNew()) {
            return individualEquals(o);
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        if (this.isNew()) {
            return individualHashCode();
        }
        return super.hashCode();
    }
}
