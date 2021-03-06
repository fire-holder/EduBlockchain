package edu.UNO.blockChain.form;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
/**
 * The class is the block chain mapper.
 * @author fire-holder
 *
 */
public class blockChainMapper implements RowMapper<blockChainform>{
	/**
	 * To map a row of block chain.
	 * @param rs The result set.
	 * @param rowNum The number of rows.
	 * @return The block chain that have set each data value.
	 * @throws SQLException
	 */
    @Override
	public blockChainform mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        blockChainform blkf = new blockChainform();
        
		blkf.setId(rs.getInt("id"));
        blkf.setTimestamp(rs.getString("timestamp"));
        blkf.setBlockheader(rs.getString("blockheader"));
        blkf.setNonce(rs.getString("nonce"));
        blkf.setPreviousblock(rs.getString("previousblock"));

        blkf.setSender(rs.getString("sender"));
        blkf.setReceiver(rs.getString("receiver"));
        blkf.setAmount(rs.getInt("amount"));
        blkf.setDate(rs.getString("date"));

		return blkf;
	}
}