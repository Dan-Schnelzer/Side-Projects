INSERT INTO users (first_name, last_name, username, password_hash)
   VALUES ('D', 'Schnelz', 'Eagle27', '12345');
   
INSERT INTO fishing_log (user_id, log_date, log_location, log_description)
 VALUES ('1','Jan 2022', 'somewhere in the ocean', 'caught 2 blue whales on a fly rod, I arm wrestled them, and then released them');  
 
 SELECT * FROM fishing_log; 