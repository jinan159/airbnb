package codesquad.airbnb.wish;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor // for @Entity
public class Wish {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private long accommodationId;

    @Column(nullable = false)
    private long memberId;

    public static Wish createNewWish(long accommodationId, long memberId) {
        return new Wish(null, accommodationId, memberId);
    }
}
