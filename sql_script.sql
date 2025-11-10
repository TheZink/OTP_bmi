CREATE DATABASE IF NOT EXISTS bmi_localization CHARACTER SET utf8mb4 COLLATE
utf8mb4_unicode_ci;
USE bmi_localization;
-- Stores BMI calculation results
CREATE TABLE IF NOT EXISTS bmi_results (
 id INT AUTO_INCREMENT PRIMARY KEY,
 weight DOUBLE NOT NULL,
 height DOUBLE NOT NULL,
 bmi DOUBLE NOT NULL,
 language VARCHAR(10),
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- Stores localized text for UI
CREATE TABLE IF NOT EXISTS localization_strings (
 id INT AUTO_INCREMENT PRIMARY KEY,
 `key` VARCHAR(100) NOT NULL,
 value VARCHAR(255) NOT NULL,
 language VARCHAR(10) NOT NULL
);

-- English
INSERT INTO localization_strings (`key`, value, language) VALUES
('weight', 'Weight (kg)', 'en'),
('height', 'Height (cm)', 'en'),
('calculate', 'Calculate BMI', 'en'),
('result', 'Your BMI is', 'en'),
('invalid', 'Invalid input', 'en'),
('localTime', 'Local Time', 'en'),
('clear', 'Clear', 'en'),
('info', 'Welcome to the BMI calculator. Enter your information in the fields.', 'en' );

-- French
INSERT INTO localization_strings (`key`, value, language) VALUES
('weight', 'Poids (kg)', 'fr'),
('height', 'Taille (cm)', 'fr'),
('calculate', 'Calculer IMC', 'fr'),
('result', 'Votre IMC est', 'fr'),
('invalid', 'Entrée invalide', 'fr'),
('localTime', 'Heure locale', 'fr'),
('clear', 'Champ', 'fr'),
('info',"Bienvenue sur le calculateur d'IMC. Veuillez saisir vos informations dans les champs.", 'fr');

-- Vietnamese
INSERT INTO localization_strings (`key`, value, language) VALUES
('weight', 'Cân nặng (kg)', 'vi'),
('height', 'Chiều cao (cm)', 'vi'),
('calculate', 'Tính BMI', 'vi'),
('result', 'Chỉ số BMI của bạn là', 'vi'),
('invalid', 'Dữ liệu không hợp lệ', 'vi'),
('localTime', 'Giờ địa phương', 'vi'),
('clear', 'Thông thoáng', 'vi'),
('info', 'Chào mừng bạn đến với công cụ tính BMI. Nhập thông tin của bạn vào các trường', 'vi');

-- Urdu (make sure to use UTF-8)

INSERT INTO localization_strings (`key`, value, language) VALUES
('weight', 'وزن (کلوگرام)', 'ur'),
('height', 'قد (سینٹی میٹر)', 'ur'),
('calculate', 'BMI کریں حساب', 'ur'),
('result', 'ہے BMI آپ کا', 'ur'),
('invalid', 'غلط ان پٹ', 'ur'),
('localTime', 'مقامی وقت ', 'ur'),
('clear', ' صاف', 'ur'),
('info', 'BMI کیلکولیٹر میں خوش آمدید۔ کھیتوں میں اپنی معلومات درج کریں۔', 'ur');

--Finnish
INSERT INTO localization_strings (`key`, value, language) VALUES
('weight', 'Paino (kg)', 'fi'),
('height', 'Pituus (cm)', 'fi'),
('calculate', 'Laske BMI', 'fi'),
('result', 'BMI-pisteesi on', 'fi'),
('invalid', 'Syötä kelvollinen numero', 'fi'),
('localTime', 'Paikallisaika', 'fi'),
('clear', 'Tyhjennä', 'fi'),
('info', 'Tervetuloa käyttämään BMI-laskuria. Syötä kenttiin sinun tiedot', 'fi' );

-- Japanese
INSERT INTO localization_strings (`key`, value, language) VALUES
('weight', 'パイノ (kg)', 'ja'),
('height', 'ピトゥス (cm)', 'ja'),
('calculate', '計算する', 'ja'),
('result', 'あなたのBMIスコアは', 'ja'),
('invalid', '有効な番号を入力してください。', 'ja'),
('localTime', 'パイカリサイカ', 'ja'),
('clear', 'クリア', 'ja'),
('info', 'BMI計算機へようこそ。各フィールドに情報を入力してください', 'ja');

-- Swedish
INSERT INTO localization_strings (`key`, value, language) VALUES
('weight', 'Vikt (kg)', 'sv'),
('height', 'Längd (cm)', 'sv'),
('calculate', 'Kalkylera BMI', 'sv'),
('result', 'Ditt BMI-värde är', 'sv'),
('invalid', 'Ange ett giltigt nummer', 'sv'),
('localTime', 'Lokaltid', 'sv'),
('clear', 'Rensa', 'sv'),
('info', 'Välkommen till BMI-kalkylatorn. Ange din information i fälten.', 'sv');


