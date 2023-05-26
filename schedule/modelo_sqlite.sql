CREATE TABLE IF NOT EXISTS people (
  personId INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  social_name TEXT,
  birthday DATE,
  cpf TEXT NOT NULL,
  rg TEXT,
  email TEXT,
  phone_number TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS teachers (
  teacher_id INTEGER PRIMARY KEY,
  teacher_internal_id INTEGER,
  personId INTEGER,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (personId) REFERENCES people (personId)
);

CREATE TABLE IF NOT EXISTS students (
  student_id INTEGER PRIMARY KEY AUTOINCREMENT,
  typeStudent INTEGER,
  student_internal_id INTEGER,
  personId INTEGER,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (personId) REFERENCES people (personId)
);

CREATE TABLE IF NOT EXISTS system_users (
  system_user_id INTEGER PRIMARY KEY AUTOINCREMENT,
  login TEXT,
  passwd TEXT,
  account_status INTEGER,
  personId INTEGER,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (personId) REFERENCES people (personId)
);

CREATE TABLE IF NOT EXISTS register_login (
  register_login_id INTEGER PRIMARY KEY AUTOINCREMENT,
  tolking TEXT NOT NULL,
  experied_in TIMESTAMP NOT NULL,
  user_id INTEGER,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES system_users (system_user_id)
);

CREATE TABLE IF NOT EXISTS defense (
  defense_id INTEGER PRIMARY KEY AUTOINCREMENT,
  type_defense INTEGER NOT NULL,
  defense_title TEXT,
  date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  local TEXT NOT NULL,
  teacher_advisor INTEGER NOT NULL,
  student_defending INTEGER NOT NULL,
  final_pontuation REAL,
  status INTEGER,
  observation TEXT
);

CREATE TABLE IF NOT EXISTS boardOfTeachers (
  board_of_teachers_id INTEGER PRIMARY KEY AUTOINCREMENT,
  teacher INTEGER NOT NULL,
  defense_id INTEGER NOT NULL,
  FOREIGN KEY (defense_id) REFERENCES defense (defense_id),
  FOREIGN KEY (teacher) REFERENCES teachers (teacher_id)
);

ALTER TABLE people ADD COLUMN teacher_id INTEGER;
ALTER TABLE people ADD COLUMN student_id INTEGER;
ALTER TABLE people ADD COLUMN system_user_id INTEGER;
