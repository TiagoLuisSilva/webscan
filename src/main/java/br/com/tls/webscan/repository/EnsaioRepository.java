package br.com.tls.webscan.repository;
 


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tls.webscan.entity.EnsaioVO;
import br.com.tls.webscan.interfaces.EnsaioInterface;

@Transactional(rollbackFor = Exception.class)
public interface EnsaioRepository extends JpaRepository<EnsaioVO, Long>, EnsaioInterface {

}
