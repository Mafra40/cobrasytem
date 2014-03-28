/*
 * Copyright (c) 2014 WISE.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    WISE - initial API and implementation and/or initial documentation
 */

package app.model.frequencia;

import app.model.atleta.Atleta;

/**
 *
 * @author WISE
 */
public class Frequencia extends Atleta{
    
    private String prensenca;
    private String dataF;

    public String getPrensenca() {
        return prensenca;
    }

    public void setPrensenca(String prensenca) {
        this.prensenca = prensenca;
    }

    public String getDataF() {
        return dataF;
    }

    public void setDataF(String dataF) {
        this.dataF = dataF;
    }
    
    
    
    
}
