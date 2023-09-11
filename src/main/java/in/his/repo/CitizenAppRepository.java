package in.his.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.his.Entity.CitizenAppEntity;

public interface CitizenAppRepository extends JpaRepository<CitizenAppEntity, Serializable> {

}
