# TEORIGY AI — Android v0.1.0

İlk sürüm, Kotlin + Jetpack Compose ile hazırlanmış Android arayüz prototipidir.

## Özellikler
- Türkçe sohbet arayüzü
- Mesaj yazma ve ekranda gösterme
- Yeni sohbet düğmesi
- GitHub Actions ile bulutta APK derleme

> Bu sürüm henüz gerçek bir yapay zekâ modeline bağlı değildir. Gönderilen mesajlara örnek/yer tutucu yanıt verir. Yerel model, kalıcı hafıza, ses, dosya analizi ve kontrollü araç kullanımı sonraki aşamalarda eklenecektir.

## GitHub üzerinden APK oluşturma
1. Bu ZIP içindeki dosyaları `TanerK26/Teorigy-Ai` deposunun ana dizinine yükle.
2. GitHub'da **Actions** sekmesini aç.
3. `Build TEORIGY AI APK` iş akışını seçip **Run workflow** düğmesine bas.
4. İşlem tamamlandığında çalışmanın altındaki **Artifacts** bölümünden `TEORIGY-AI-debug-apk` dosyasını indir.
5. ZIP'i açıp `app-debug.apk` dosyasını Android telefona kur.

## Gereksinimler
- Android 8.0 (API 26) veya üzeri
- Derleme: JDK 17, Android SDK 35, Gradle 8.11.1
