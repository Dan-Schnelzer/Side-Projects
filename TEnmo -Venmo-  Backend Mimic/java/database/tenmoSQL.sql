SELECT transfers.transfer_id ,transfers.amount,  d.username FROM transfers
 JOIN accounts a ON transfers.account_from = a.account_id 
 JOIN accounts b ON transfers.account_to = b.account_id
               JOIN users c ON a.user_id = c.user_id 
               JOIN users d ON b.user_id = d.user_id
                WHERE a.user_id = 1001 OR b.user_id = 1001;
      
         
                
    SELECT transfers.transfer_id, transfers.account_from, transfers.account_to, transfer_statuses.transfer_status_desc, transfer_types.transfer_type_desc, amount FROM transfers
    JOIN transfer_types ON transfer_types.transfer_type_id = transfers.transfer_type_id
    JOIN transfer_statuses ON transfer_statuses.transfer_status_id = transfers.transfer_status_id
    WHERE transfer_id = 3001;                                                                            -- halfway plan for transfer details                  
                
                
-- UNION SELECT username FROM  users
  --      JOIN accounts ON accounts.user_id = users.user_id
  --      JOIN transfers ON transfers.account_to = accounts.account_id
   --     WHERE transfers.account_from = accounts.user_id AND accounts.user_id = 1001;               
   
   
   
   SELECT transfers.transfer_id ,transfers.amount, users.username FROM transfers
        JOIN accounts ON transfers.account_from = accounts.account_id
        JOIN users ON accounts.user_id = users.user_id
        WHERE transfers.account_from = 1001;
        
        
UNION
SELECT transfers.transfer_id ,transfers.amount, users.username FROM transfers
JOIN accounts ON transfers.account_to = accounts.account_id
JOIN users ON accounts.user_id = users.user_id
WHERE transfers.account_to =  1003 OR transfers.account_from = 1003;



--***********************************************************************************************--

     
--NOTE: Includes seperate columns with user_from and user_to information for each transfer     
SELECT t.transfer_id ,t.amount, c.username AS user_to, d.username AS user_from FROM transfers t
 JOIN accounts a ON t.account_from = a.account_id 
 JOIN accounts b ON t.account_to = b.account_id
               JOIN users c ON a.user_id = c.user_id 
               JOIN users d ON b.user_id = d.user_id
               WHERE a.user_id = 1001 OR b.user_id = 1001;   
     
--NOTE: Includes seperate columns with user_from and user_to information for transfer details   
  SELECT t.transfer_id, ts.transfer_status_desc, tt.transfer_type_desc, amount, c.username AS user_to, d.username AS user_from  FROM transfers t
    JOIN accounts a ON t.account_from = a.account_id 
    JOIN accounts b ON t.account_to = b.account_id
    JOIN users c ON a.user_id = c.user_id 
    JOIN users d ON b.user_id = d.user_id
    JOIN transfer_types tt ON tt.transfer_type_id = t.transfer_type_id
    JOIN transfer_statuses ts ON ts.transfer_status_id = t.transfer_status_id
    WHERE transfer_id = 3001;    