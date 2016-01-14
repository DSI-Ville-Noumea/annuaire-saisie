/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nc.noumea.mairie.annuairev2.saisie.service;

/*
 * #%L
 * Gestion des Guest et Locality
 * %%
 * Copyright (C) 2015 - 2016 Mairie de Nouméa
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

import java.util.List;
import nc.noumea.mairie.annuairev2.saisie.core.security.AnnuaireSaisiePerm;
import nc.noumea.mairie.annuairev2.saisie.entity.Sectorisation;
import org.springframework.security.access.annotation.Secured;

/**
 *
 * @author barmi83
 */
public interface ISectorisationService {
    
    public static final String BEAN_ID = "sectorisationService";
    
    @Secured({ AnnuaireSaisiePerm.GUEST_CONSULT, AnnuaireSaisiePerm.LOCALITY_CONSULT })
    public Sectorisation findByLibelle(String libelle);

    @Secured({ AnnuaireSaisiePerm.GUEST_CONSULT, AnnuaireSaisiePerm.LOCALITY_CONSULT  })
    public List<Sectorisation> findAll();
    
    @Secured({ AnnuaireSaisiePerm.GUEST_CONSULT, AnnuaireSaisiePerm.LOCALITY_CONSULT  })
    public Sectorisation findById(Long id);
    
}
