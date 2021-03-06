package edu.UNO.blockChain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import edu.UNO.blockChain.form.Broadcastform;
import edu.UNO.blockChain.form.blockChainform;
import edu.UNO.blockChain.form.Tokenform;
import edu.UNO.blockChain.form.blockChainMapper;
import edu.UNO.blockChain.form.TokenMapper;
import edu.UNO.blockChain.form.Pollform;
import edu.UNO.blockChain.form.PollMapper;

/**
 * Repository that stores the broadcast report.
 * @author fire-holder
 *
 */
@Repository
public class Broadcastrepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Insert the broadcast report into transaction poll table.
     * @param createForm The broadcast object to be updated.
     * @param user The user of the broadcast report.
     * @return The number of rows that are updated.
     */
    public int createTransaction(Broadcastform createForm, String user) {
        String sql = "insert into transactionpoll(id, amount, fee, hash, date, sender, receiver) values(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, 1, createForm.getAmount(), 1, createForm.getHashid(), new Date().getTime(),
                user,createForm.getReceiver());
    }

    /**
     * Select the broadcast report by token.
     * @param user The owner of the token.
     * @return The result list.
     */
    public List<Tokenform> findByTokens(String user) {
        return jdbcTemplate.query("SELECT id,token,owner FROM token where owner = "+user, new TokenMapper());

    }

    /**
     * Select the broadcast report by transaction poll.
     * @return The result list.
     */
    public List<Pollform> findByPolls() {

        return jdbcTemplate.query("SELECT id,amount,fee,hash,date,sender,receiver FROM transactionpoll", new PollMapper());

    }

    /**
     * Select the broadcast report by card view.
     * @return The result list.
     */
    public List<blockChainform> findbyCardView() {

        return jdbcTemplate.query("SELECT * FROM blockChain",
                new blockChainMapper());
    }
    /**
     * Select the broadcast report by keyword.
     * @param keyword The keyword to be searched.
     * @return The result list.
     */
    public List<blockChainform> findByKeyword(String keyword) {
        
        return jdbcTemplate.query("SELECT * FROM blockchain b where b.blockheader like '%"
                + keyword + "%' or b.transaction like '%" + keyword + "%'", new blockChainMapper());
    }


}
