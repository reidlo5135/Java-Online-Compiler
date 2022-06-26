package com.jcompile.reidlo.compiler.model;

import com.jcompile.reidlo.config.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "base_compile_data")
@NoArgsConstructor
@AllArgsConstructor
public class BaseCompileData extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "beforeData")
    private String beforeCompile;

    @Column(name = "afterData")
    private String afterCompile;
}
