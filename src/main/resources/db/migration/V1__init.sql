CREATE TABLE IF NOT EXISTS users (
  id           BIGINT PRIMARY KEY AUTO_INCREMENT,
  email        VARCHAR(190) NOT NULL UNIQUE,
  nickname     VARCHAR(100) NOT NULL,
  birth_date   DATE NULL,
  role         VARCHAR(20) NOT NULL DEFAULT 'USER',           -- USER, ADMIN (권한만 보유, 보안 미적용)
  status       VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',         -- ACTIVE, SUSPENDED, DELETED
  created_at   DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at   DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);

CREATE INDEX idx_users_status ON users(status);
CREATE INDEX idx_users_email  ON users(email);
