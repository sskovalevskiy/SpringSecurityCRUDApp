package spring.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.security.model.Role;

/**
 * @author ss.kovalevskiy
 * @version 1.0
 */
public interface RoleDao extends JpaRepository<Role, Long> {
}
