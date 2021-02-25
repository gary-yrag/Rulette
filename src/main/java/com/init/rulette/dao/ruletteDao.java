package com.init.rulette.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.init.rulette.config.Conexion;
import com.init.rulette.entitys.rulette;

public class ruletteDao {
	Conexion c = new Conexion();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	/**
	 * Search Rulette
	 * @param rulette_id
	 * @return
	 * @throws Exception
	 */
	public rulette ruletteSearch(int rulette_id) throws Exception {
		rulette r = new rulette();
		
		try {
			con=c.conectar();
			
			String sql = "SELECT ID, STATUS FROM RULETTE WHERE ID=?";
			
			ps=con.prepareStatement(sql);
			ps.setInt(0,rulette_id);
			
			rs = ps.executeQuery();
			
			if(rs.getFetchSize()>=1) {
				r.setId(rs.getInt("ID"));
				r.setStatus(rs.getString("STATUS"));
			}	
			
		}catch(Exception e) {
			throw new Exception(e);
		}
		return r;		
	}
	
	public List<rulette> rulettes() throws Exception{
		List<rulette> ru = new ArrayList<rulette>();
		
		try {
			con=c.conectar();			
			String sql = "SELECT ID, STATUS FROM RULETTE";			
			ps=con.prepareStatement(sql);			
			rs = ps.executeQuery();
			
			if(rs.getFetchSize()>=1) {
				while(rs.next()) {
					rulette r = new rulette();
					r.setId(rs.getInt(0));
					r.setStatus(rs.getString("STATUS"));
					
					ru.add(r);
				}
			}	
			
		}catch(Exception e) {
			throw new Exception(e);
		}
		return ru;			
	} 
}


