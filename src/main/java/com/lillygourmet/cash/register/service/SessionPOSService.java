/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.service;
/**
 * SessionPOS Service
 *
 * @author Alias King - Younes OUFRID
 */
import com.lillygourmet.cash.register.model.SessionPOS;
import com.lillygourmet.cash.register.repository.SessionPOSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SessionPOSService {

	@Autowired
	private SessionPOSRepository SessionPOSRepository;


	public List<SessionPOS> retrieveAllSessionPOSs() {
		return SessionPOSRepository.findAll();
	}

	public SessionPOS getSessionPOS(Long id) {
		return SessionPOSRepository.getOne(id);
	}

	public SessionPOS createSessionPOS(SessionPOS SessionPOS) {
		return SessionPOSRepository.save(SessionPOS);
	}

	@Transactional
	public void deleteSessionPOS(Long id) throws Exception {

		SessionPOS SessionPOS = SessionPOSRepository.getOne(id);
		if (SessionPOS != null) {
			SessionPOSRepository.deleteById(id);
		} else {
			throw new RuntimeException("Record not found");
		}
	}

	public SessionPOS updateSessionPOS(SessionPOS SessionPOS) {
		SessionPOS editedSessionPOS = SessionPOSRepository.getOne(SessionPOS.getId());
		editedSessionPOS.setOpenMontant(SessionPOS.getOpenMontant());
		editedSessionPOS.setCloseMontant(SessionPOS.getCloseMontant());
		editedSessionPOS.setState(SessionPOS.getState());
		editedSessionPOS.setUserCaissier(SessionPOS.getUserCaissier());
		editedSessionPOS.setComment(SessionPOS.getComment());
		return SessionPOSRepository.save(editedSessionPOS);
	}
}
