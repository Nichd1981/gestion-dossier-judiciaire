package be.tftic.java.domain.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Classe représentant un téléphone.
 * Cette classe permet de stocker les informations relatives à un téléphone,
 * telles que son numéro et son libellé, ainsi que la personne à laquelle il
 * est associé.
 *
 */
@Entity
@Table(name = "PHONE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Phone {

    /**
     * Identifiant unique du téléphone.
     * Cet identifiant est généré automatiquement par la base de données lorsque
     * le téléphone est créé.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Numéro du téléphone.
     * Ce numéro est obligatoire et doit être unique pour chaque téléphone. Il
     * peut être utilisé pour appeler ou envoyer des messages à la personne à
     * laquelle le téléphone est associé.
     */
    @Setter
    @Column(name = "NUMBER", nullable = false)
    private String number;

    /**
     * Libellé du téléphone.
     * Ce libellé est obligatoire et permet de donner un nom ou une description
     * au téléphone (par exemple : "téléphone personnel", "téléphone
     * professionnel", "téléphone fixe", etc.).
     */
    @Setter
    @Column(name = "LABEL", nullable = false)
    private String label;

    /**
     * Personne à laquelle le téléphone est associé.
     * Cette personne est obligatoire et doit être une instance de la classe
     * Personne. Elle permet de lier le téléphone à une personne
     * en particulier et de retrouver facilement tous les téléphones associés à
     * une personne.
     */
    @ManyToOne
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person person;

    /**
     * Constructeur de la classe Telephone.
     * Ce constructeur permet de créer une nouvelle instance de la classe
     * Telephone en définissant son numéro, son libellé et la
     * personne à laquelle il est associé.
     *
     * @param number     le numéro du téléphone
     * @param label    le libellé du téléphone
     * @param person   la personne à laquelle le téléphone est associé
     */
    @Builder
    public Phone(String number, String label, Person person) {
        this.number = number;
        this.label = label;
        this.person = person;
    }
}
