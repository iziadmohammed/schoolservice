package com.stc.fileservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;

@Entity
@Table(name = "PermissionGroup")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PermissionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "group_name")
    @Unique
    private String groupName;

}
