package be.vdab.services;

import be.vdab.repositories.CursusRepository;

import java.util.List;

import be.vdab.entities.Cursus;

public class CursusService extends AbstractService {
	private final CursusRepository cursusRepository = new CursusRepository();
	public List<Cursus> findByNaamContains(String woord) {
		return cursusRepository.findByNaamContains(woord);
	}
}
