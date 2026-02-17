package ai.neuron.copilot.knowledge.foundation.data.rdb.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaAuditingRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    default boolean softDelete(ID id) {
        return findById(id).map(po -> {
            delete(po);
            return true;
        }).orElse(false);
    }

}
