package com.stc.fileservice.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stc.fileservice.model.enums.ItemType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Item")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonSerialize
@ToString
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ItemType type;
    @OneToOne
    private PermissionGroup permissionGroup;
    @OneToOne
    private Item parentItem;

}
