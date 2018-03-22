/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spartan.AccountsDemo.dao.procedures;

import com.spartan.AccountsDemo.dao.pojo.AltaStoredProcedureResponse;
import java.sql.JDBCType;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 *
 * @author tagle
 */
public class AltaStoredProcedure extends StoredProcedure {

    public AltaStoredProcedure(DataSource dataSource) {
        super(dataSource, "alta");
    }

    public AltaStoredProcedureResponse execute(
            String nombre,
             String telefono,
             String direccion,
             String correo,
             Double saldo_in,
             String ejecutivo
    ) {
        Map<String, Object> inMap = new HashMap<>();
        inMap.put("nombre", nombre);
        inMap.put("telefono", telefono);
        inMap.put("direccion", direccion);
        inMap.put("correo", correo);
        inMap.put("saldo_in", saldo_in);
        inMap.put("ejecutivo", ejecutivo);
        inMap.put("cliente_id", 0);
        inMap.put("cuenta_id", 0);
        inMap.put("saldo", 0d);
        super.declareParameter(new SqlParameter("nombre", Types.VARCHAR));
        super.declareParameter(new SqlParameter("telefono", Types.VARCHAR));
        super.declareParameter(new SqlParameter("direccion", Types.VARCHAR));
        super.declareParameter(new SqlParameter("correo", Types.VARCHAR));
        super.declareParameter(new SqlParameter("saldo_in", Types.DECIMAL));
        super.declareParameter(new SqlParameter("ejecutivo", Types.VARCHAR));
        super.declareParameter(new SqlOutParameter("cliente_id", Types.INTEGER));
        super.declareParameter(new SqlOutParameter("cuenta_id", Types.INTEGER));
        super.declareParameter(new SqlOutParameter("saldo", Types.DECIMAL));
        Map<String, Object> out = super.execute(inMap);
        AltaStoredProcedureResponse response = new AltaStoredProcedureResponse();
        response.setClienteId((int) out.get("cliente_id"));
        response.setCuentaId((int) out.get("cuenta_id"));
        response.setSaldo((Double) out.get("saldo"));
        return response;
    }

}
