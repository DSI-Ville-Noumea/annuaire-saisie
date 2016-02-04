package nc.noumea.mairie.annuairev2.saisie.service;

/*
 * #%L
 * Gestion des Guest et Locality
 * %%
 * Copyright (C) 2015 Mairie de Nouméa
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import nc.noumea.mairie.annuairev2.saisie.entity.Guest;

import java.util.List;
import nc.noumea.mairie.annuairev2.saisie.core.security.AnnuaireSaisiePerm;
import nc.noumea.mairie.annuairev2.saisie.entity.GuestInfo;
import org.springframework.security.access.annotation.Secured;

/**
 * Created by barmi83 on 30/12/15.
 */
public interface IGuestService {

    public static final String BEAN_ID = "guestService";

    @Secured({ AnnuaireSaisiePerm.GUEST_CONSULT })
    public List<Guest> findAll();
    
    @Secured({ AnnuaireSaisiePerm.GUEST_CONSULT })
    public List<GuestInfo> findAllGuestInfo();
    
    @Secured({ AnnuaireSaisiePerm.GUEST_CONSULT })
    public Guest findById(Long id);
    
    @Secured({ AnnuaireSaisiePerm.GUEST_CONSULT })
    public Guest findByIdentifiant(String identifiant);
    
    @Secured(AnnuaireSaisiePerm.GUEST_ADD)
    public Guest saveOrUpdate(Guest guest);
    
    @Secured({ AnnuaireSaisiePerm.GUEST_DEL })
    public void deleteById(Long id);
    
    @Secured({ AnnuaireSaisiePerm.GUEST_CONSULT })
    public List<Guest> findByNomEtService(String nom, String service);
    
    @Secured({ AnnuaireSaisiePerm.GUEST_CONSULT })
    public List<GuestInfo> findGuestInfoByNomEtService(String nom, String service);


}
