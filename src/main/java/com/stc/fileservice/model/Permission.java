package com.stc.fileservice.model;

import com.stc.fileservice.model.enums.PermissionLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_email")
    @Unique
    private String userEmail;

    @Enumerated(EnumType.STRING)
    private PermissionLevel permissionLevel;
    @OneToOne
    private PermissionGroup permissionGroup;
}
