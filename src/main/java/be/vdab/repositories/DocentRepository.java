package be.vdab.repositories;

import java.util.Optional;

import be.vdab.entities.Docent;

public class DocentRepository extends AbstractRepository {
	public Optional<Docent> read(long id) {
		return Optional.ofNullable(getEntityManager().find(Docent.class, id));
	}
	public void create(Docent docent) {
		getEntityManager().persist(docent);
	}
	public void delete(long id) {
		read(id)
			.ifPresent(docent -> getEntityManager().remove(docent));
	}
}
