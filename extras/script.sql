-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.7.11 - MySQL Community Server (GPL)
-- OS do Servidor:               Win32
-- HeidiSQL Versão:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura para tabela livraria.author
CREATE TABLE IF NOT EXISTS `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela livraria.author: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` (`id`, `name`) VALUES
	(1, 'Isaac Newton'),
	(2, 'Carlos Calhourdo'),
	(3, 'Rubalinho dos anjos');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;

-- Copiando estrutura para tabela livraria.book
CREATE TABLE IF NOT EXISTS `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `isbn` varchar(100) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_author_id` (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela livraria.book: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`id`, `title`, `description`, `isbn`, `stock`, `author_id`) VALUES
	(1, 'Palavras de Inri Cristo', 'Inri Cristo', '12365412', 2, 1),
	(2, 'Supercampeões - Uma história', 'A história dos supercampeões, toda a sua saga até o final', '3214565', 5, 3),
	(3, 'PostMan edição', 'Edição via postman. Exemplo prático', '333333', 7, 3),
	(4, 'Passarinho Feliz', 'A incrível história do passaro', '12365412AAtR', 6, 1),
	(5, 'Passarinho Feliz', 'A incrível história do passaro', '12365412AAtR', 6, 1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
