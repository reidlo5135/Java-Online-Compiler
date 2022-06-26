package com.jcompile.reidlo.compiler.repo;

import com.jcompile.reidlo.compiler.model.BaseCompileData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseCompileDataRepository extends JpaRepository<BaseCompileData, Long> {
}
