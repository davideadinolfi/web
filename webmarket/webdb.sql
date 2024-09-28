-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Set 28, 2024 alle 23:33
-- Versione del server: 10.4.25-MariaDB
-- Versione PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `webdb`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `caratteristica`
--

CREATE TABLE `caratteristica` (
  `id` int(11) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `descrizione` varchar(255) NOT NULL,
  `id_categoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `caratteristica`
--

INSERT INTO `caratteristica` (`id`, `nome`, `descrizione`, `id_categoria`) VALUES
(1, 'CPU', 'cpu', 5),
(3, 'RAM', 'ram', 5),
(5, 'batteria', 'batteria', 6);

-- --------------------------------------------------------

--
-- Struttura della tabella `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `descrizione` varchar(255) NOT NULL,
  `id_categoriaPadre` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `categoria`
--

INSERT INTO `categoria` (`id`, `nome`, `descrizione`, `id_categoriaPadre`) VALUES
(4, 'pc', 'pc', NULL),
(5, 'pc fisso', 'pc fisso', 4),
(6, 'pc portatile', 'pc portatile', 4),
(7, 'monitor', 'monitor', NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `notifica`
--

CREATE TABLE `notifica` (
  `ID` int(11) NOT NULL,
  `ID_utente` int(11) NOT NULL,
  `ID_richiesta_acquisto` int(11) NOT NULL,
  `messaggio` varchar(255) NOT NULL,
  `data` datetime NOT NULL,
  `visualizzato` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `proposta_acquisto`
--

CREATE TABLE `proposta_acquisto` (
  `id` int(11) NOT NULL,
  `id_richiesta_acquisto` int(11) NOT NULL,
  `nome_produttore` varchar(64) NOT NULL,
  `nome_prodotto` varchar(64) NOT NULL,
  `codice_prodotto` varchar(64) NOT NULL,
  `prezzo` double NOT NULL,
  `url` varchar(264) NOT NULL,
  `note` varchar(1024) NOT NULL,
  `stato_proposta` enum('in attesa','approvato','respinto','ordinato','terminato','NC','NF') NOT NULL,
  `nota_respinta` varchar(1024) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `proposta_acquisto`
--

INSERT INTO `proposta_acquisto` (`id`, `id_richiesta_acquisto`, `nome_produttore`, `nome_prodotto`, `codice_prodotto`, `prezzo`, `url`, `note`, `stato_proposta`, `nota_respinta`) VALUES
(13, 1, 'zzzzz', 'trapphone', 'aaaaaa', 7.77, 'tttttt', 'grrgrgr', 'in attesa', NULL),
(16, 1, 'zzzzz', 'trapphone', 'aaaaaa', 7.77, 'tttttt', 'grrgrgr', 'in attesa', NULL),
(24, 1, 'fdsafdas', 'fadsadfs', 'afdsafsd', 30.56, 'fadssadf', 'fadsfads', 'in attesa', NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `richiesta_acquisto`
--

CREATE TABLE `richiesta_acquisto` (
  `id` int(11) NOT NULL,
  `id_ordinante` int(11) NOT NULL,
  `id_tecnico` int(11) DEFAULT NULL,
  `id_categoria` int(11) NOT NULL,
  `data_richiesta` datetime NOT NULL,
  `note` varchar(1024) DEFAULT NULL,
  `stato_richiesta` enum('attesaTecnico','attesaOrdinante','ordinato','concluso') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `richiesta_acquisto`
--

INSERT INTO `richiesta_acquisto` (`id`, `id_ordinante`, `id_tecnico`, `id_categoria`, `data_richiesta`, `note`, `stato_richiesta`) VALUES
(1, 4, 5, 5, '2024-09-22 19:36:20', 'note', 'attesaTecnico'),
(2, 4, 5, 7, '2024-09-24 14:57:45', 'aaa', 'attesaTecnico'),
(3, 4, NULL, 6, '2024-09-24 14:57:51', 'vvvv', 'attesaTecnico');

-- --------------------------------------------------------

--
-- Struttura della tabella `richiesta_caratteristica`
--

CREATE TABLE `richiesta_caratteristica` (
  `id` int(11) NOT NULL,
  `id_richiesta_acquisto` int(11) NOT NULL,
  `id_caratteristica` int(11) NOT NULL,
  `specifica` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `richiesta_caratteristica`
--

INSERT INTO `richiesta_caratteristica` (`id`, `id_richiesta_acquisto`, `id_caratteristica`, `specifica`) VALUES
(1, 1, 1, 'caratteristica1'),
(2, 1, 3, 'caratteristica2'),
(3, 3, 5, 'cccc');

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `id` int(11) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `cognome` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  `password` varchar(128) NOT NULL,
  `ruolo` enum('admin','tecnico','utente') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`id`, `nome`, `cognome`, `email`, `password`, `ruolo`) VALUES
(1, 'admin', 'admin', 'admin@email.com', 'admin', 'admin'),
(3, 'a', 'a', 'a', '60bddb4f4d5b4317d3faec87756f77909e0a2e6ab80e8a8bae3eceefeb354ffe52a2414e67734acd6b2b151f1ca271b6', 'admin'),
(4, 'u', 'u', 'u', '8b0239756676a129d02e725035c89edd641112b47836a5bcddce1fb082d3201dbb919a523c0fd13d91de75c942d10ac5', 'utente'),
(5, 't', 't', 't', '641869496bb3687c33b9b74d56c720317f68d61b40284a1de91b8132c5ca9da19635c24ad75c3330877567135be1d0be', 'tecnico'),
(6, 'vvcvxv', 'vcxvcx', 'ciaooo', 'af67255b2ee23589d2499995f758aca5a9041f44373481be41d946448ab49ae85f27e7f4c012d2e537af1e79f950c59a', 'utente');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `caratteristica`
--
ALTER TABLE `caratteristica`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_categoria` (`id_categoria`);

--
-- Indici per le tabelle `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nome` (`nome`),
  ADD KEY `id_categoriaPadre` (`id_categoriaPadre`);

--
-- Indici per le tabelle `notifica`
--
ALTER TABLE `notifica`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_utente` (`ID_utente`),
  ADD KEY `ID_richiesta_acquisto` (`ID_richiesta_acquisto`);

--
-- Indici per le tabelle `proposta_acquisto`
--
ALTER TABLE `proposta_acquisto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_richiesta_acquisto` (`id_richiesta_acquisto`);

--
-- Indici per le tabelle `richiesta_acquisto`
--
ALTER TABLE `richiesta_acquisto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_ordinante` (`id_ordinante`),
  ADD KEY `id_tecnico` (`id_tecnico`),
  ADD KEY `id_categoria` (`id_categoria`);

--
-- Indici per le tabelle `richiesta_caratteristica`
--
ALTER TABLE `richiesta_caratteristica`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_richiesta_acquisto` (`id_richiesta_acquisto`),
  ADD KEY `id_caratteristica` (`id_caratteristica`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `caratteristica`
--
ALTER TABLE `caratteristica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT per la tabella `notifica`
--
ALTER TABLE `notifica`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `proposta_acquisto`
--
ALTER TABLE `proposta_acquisto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT per la tabella `richiesta_acquisto`
--
ALTER TABLE `richiesta_acquisto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `richiesta_caratteristica`
--
ALTER TABLE `richiesta_caratteristica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `categoria`
--
ALTER TABLE `categoria`
  ADD CONSTRAINT `categoria_ibfk_1` FOREIGN KEY (`id_categoriaPadre`) REFERENCES `categoria` (`id`);

--
-- Limiti per la tabella `notifica`
--
ALTER TABLE `notifica`
  ADD CONSTRAINT `notifica_ibfk_1` FOREIGN KEY (`ID_utente`) REFERENCES `utenti` (`id`),
  ADD CONSTRAINT `notifica_ibfk_2` FOREIGN KEY (`ID_richiesta_acquisto`) REFERENCES `richiesta_acquisto` (`id`);

--
-- Limiti per la tabella `proposta_acquisto`
--
ALTER TABLE `proposta_acquisto`
  ADD CONSTRAINT `proposta_acquisto_ibfk_1` FOREIGN KEY (`id_richiesta_acquisto`) REFERENCES `richiesta_acquisto` (`id`);

--
-- Limiti per la tabella `richiesta_acquisto`
--
ALTER TABLE `richiesta_acquisto`
  ADD CONSTRAINT `richiesta_acquisto_ibfk_1` FOREIGN KEY (`id_ordinante`) REFERENCES `utenti` (`id`),
  ADD CONSTRAINT `richiesta_acquisto_ibfk_2` FOREIGN KEY (`id_tecnico`) REFERENCES `utenti` (`id`),
  ADD CONSTRAINT `richiesta_acquisto_ibfk_3` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`);

--
-- Limiti per la tabella `richiesta_caratteristica`
--
ALTER TABLE `richiesta_caratteristica`
  ADD CONSTRAINT `richiesta_caratteristica_ibfk_1` FOREIGN KEY (`id_richiesta_acquisto`) REFERENCES `richiesta_acquisto` (`id`),
  ADD CONSTRAINT `richiesta_caratteristica_ibfk_2` FOREIGN KEY (`id_caratteristica`) REFERENCES `caratteristica` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
