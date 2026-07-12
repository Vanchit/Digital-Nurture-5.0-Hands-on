-- Create country table
CREATE TABLE IF NOT EXISTS country (
    co_code VARCHAR(2) PRIMARY KEY,
    co_name VARCHAR(50)
);

-- Insert sample records
INSERT IGNORE INTO country VALUES ('IN', 'India');
INSERT IGNORE INTO country VALUES ('US', 'United States of America');
