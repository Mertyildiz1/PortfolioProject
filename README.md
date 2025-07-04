# PortfolioProject

![Java CI](https://github.com/<KULLANICI_ADI>/<REPO_ADI>/actions/workflows/ci.yml/badge.svg)

Proje Türü: Portfolio Test Projesi  
Hedef: Test otomasyon becerilerini sergilemek, Best Practice

## CI/CD (Sürekli Entegrasyon)

- Projede GitHub Actions ile otomatik test çalıştırma yapılandırılmıştır.
- Her push ve her gün saat 13:30'da testler otomatik olarak çalışır.
- Test raporları otomatik olarak oluşturulur ve workflow sonunda erişilebilir.

Workflow dosyası: `.github/workflows/ci.yml`

# Portfolio Test Project

Bu proje, test otomasyon becerilerimi sergileyen bir portfolio projesidir.  
**Automation Exercise** web sitesi üzerinde hem manuel hem de otomatik test süreçlerini, gerçekçi ve profesyonel bir yaklaşımla kapsamlı şekilde içermektedir.

---

## 🚀 Özellikler

- **Selenium WebDriver** ile web otomasyonu
- **RestAssured** ile API otomasyon testleri
- **TestNG** framework'ü ile test yönetimi
- **ExtentReports** ile detaylı ve görsel test raporlama
- **Page Object Model** (POM) tasarım deseni ile sürdürülebilir kod
- **Dinamik veri üretimi** (her testte benzersiz email ile kayıt)
- **Testler arası veri paylaşımı** (TestDataHolder ile)
- **Otomatik screenshot alma** (test başarısız olduğunda)
- **Maven** ile dependency yönetimi
- **Listeners** ile WebDriver ve test yaşam döngüsü yönetimi
- **Konfigürasyon dosyası** ile merkezi test verisi ve ortam yönetimi

---

## 🛠️ Kullanılan Teknolojiler

- Java 23
- Selenium WebDriver 4.32.0
- RestAssured 5.4.0
- TestNG 7.10.2
- ExtentReports 4.0.9
- JavaFaker 1.0.2
- Apache POI 5.2.5
- Maven 3.x

---

## 🧪 Test Kapsamı

### Web UI Testleri
- Kayıt işlemi testleri (başarılı/başarısız)
- Giriş işlemi testleri (başarılı/başarısız)
- Form validasyon ve hata mesajı kontrol testleri
- Ürün arama, sepete ekleme, ödeme, iletişim formu vb. senaryolar
- Data-driven ve POM ile sürdürülebilir testler

### API Testleri
- Ürün, marka, kullanıcı işlemleri için GET, POST, PUT, DELETE endpoint testleri
- Pozitif ve negatif senaryolar (ör: geçersiz method, eksik parametre, yanlış login)
- Dinamik veri üretimi ve testler arası veri aktarımı (TestDataHolder ile)
- TestNG ile bağımlı test akışı (`dependsOnMethods`)
- API testlerinde gerçekçi iş akışları (kullanıcı oluştur, detayını sorgula, sil)

---

## 📊 Test Raporları

- **ExtentReports** ile HTML formatında detaylı test raporları otomatik olarak `src/test/resources/reports/` klasörüne oluşturulur.
- Testler başarısız olduğunda otomatik olarak screenshot alınır ve rapora eklenir.
- Raporlarda her testin sonucu, hata mesajları ve performans metrikleri yer alır.

---

## 📝 Test Metodolojisi

- **Page Object Model (POM):** Kod tekrarını önlemek ve bakım kolaylığı için
- **Data-Driven Testing:** Farklı test verileriyle kapsamı genişletmek için
- **TestNG Listeners:** Test yaşam döngüsünü merkezi yönetmek için
- **Dinamik ve izole testler:** Her API testinde benzersiz veri ile tekrarlanabilirlik
- **Testler arası bağımlılık:** Gerçek iş akışını simüle etmek için dependsOnMethods
- **Config dosyası:** Tüm test verileri ve ortam ayarları merkezi olarak yönetilir
- **Kapsamlı coverage:** Hem pozitif hem negatif senaryolar test edilir

---

## 🎯 Portfolio Özellikleri

Bu proje aşağıdaki becerileri sergiler:

- Test otomasyon geliştirme (UI + API)
- Test planlama ve test case yazma
- Modern test araçları ve framework'leri kullanımı
- Kod organizasyonu ve best practices
- Test raporlama ve dokümantasyon
- Hata ayıklama ve dinamik veri yönetimi

---

## 📁 Proje Yapısı

```
PortfolioProject/
│
├── src/
│   └── test/
│       ├── java/
│       │   ├── apiTests/         # API test classları
│       │   ├── tests/            # Web UI test classları (POM)
│       │   └── utilities/        # ConfigReader, Driver, DataProviderClass, Listeners, RetryAnalyzer, ReusableMethods, TestDataHolder
│       └── resources/
│           └── reports/          # ExtentReports HTML raporları
│
├── configuration.properties      # Test verileri ve ortam ayarları
├── pom.xml                      # Maven bağımlılıkları
├── testng.xml                   # TestNG suite ve test sırası
└── README.md                    # Proje dokümantasyonu
```

---

## 📝 Not

Bu proje, teknik bilgi ve test otomasyon pratiklerini göstermek için hazırlanmıştır.  
Her türlü öneri ve geri bildirim için iletişime geçebilirsiniz.

## 📄 Ek Dokümantasyon

- **Manuel Test Case'ler:** Tüm manuel test senaryoları ve adımları Google Sheets üzerinde detaylı olarak dokümante edilmiştir. [Manuel Test Case'ler](https://docs.google.com/spreadsheets/d/1B7jdYvrQclWU3P49Iyvyd-_lP3ViH6pIvXz7byBQ0QQ/edit?usp=sharing)
- **API Test Case'leri:** API uç noktalarına ait test senaryoları ve beklenen sonuçlar Google Sheets dokümanında yer almaktadır. [API Test Case'leri](https://docs.google.com/spreadsheets/d/1wvj83WIaFCBzdywTyjDLtB6prDvgFKvwmcg5-JS5FwM/edit?usp=sharing)
- **Bug Report'ları:** Testler sırasında tespit edilen hatalar ve raporları Google Sheets üzerinden takip edilmektedir. [Bug Report'ları](https://docs.google.com/spreadsheets/d/1edjRsi86Ca-ucRKp-nSzX2IxHkaPzippiwV8OCBMrQI/edit?usp=sharing)
- **Feature Request'ler:** Proje kapsamında önerilen yeni özellikler ve geliştirme talepleri ayrı bir Google Sheets dokümanında listelenmiştir. [Feature Request'ler](https://docs.google.com/spreadsheets/d/1KcRs4s0gbqUvuSTgvIPaOvQxHK1ATfZkfxHU1gKh5PU/edit?usp=sharing)
- **ExtentReport Test Çıktıları:** Tüm testlerin ExtentReport çıktılarını içeren Google Docs dokümanına [buradan ulaşabilirsiniz](https://docs.google.com/spreadsheets/d/10bdRwa7d9ZZnNIzRAxeSa8WiHn8Bo-XI0S0JZeHTQA4/edit?usp=sharing)

---

**Mert Yıldız**  
Proje Türü: Portfolio Test Projesi  
Hedef: Test otomasyon becerilerini sergilemek, Best Practice