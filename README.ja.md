# 🚌 スクールバス予約システム（学生用アプリ・Java版）

このアプリは、**学生がスクールバスを予約できるAndroidアプリ（Javaバージョン）**です。  
Firebaseと連携し、リアルタイムで予約を行うことができ、  
シンプルなUIでスムーズな予約体験を提供します。

- **[🟦 デモ動画を見る](https://youtube.com/shorts/VGlsEBxsMDQ?feature=share)**  
- 2024年 大邱カトリック大学 キャップストーンデザイン 🥉奨励賞 受賞作品  
- **📄 [🇰🇷 한국어版READMEはこちら](./README.md)**  

---

## 🔧 主な機能

### 1. ログイン / 会員登録
- [ログイン画面](userImages/userlogin.PNG)  
- [会員登録画面](userImages/userregister.PNG)

- Firebase Authentication を使用し、メール・パスワードによる会員登録とログイン機能を提供  
- ログイン成功時、メイン画面に遷移

---

### 2. 路線選択画面
- [画面レイアウト](userImages/userroutechoose.PNG)

- Spinner（ドロップダウン）を使って路線を選択  
- 選択した路線に基づいて、停留所と時間選択画面へ遷移  
- 路線ごとに別のXMLレイアウト画面を構成  

---

### 3. 停留所および時間の選択
- [画面レイアウト](userImages/usertimeplace.PNG)

- Spinnerで停留所と時間を選択  
- 選択した予約情報を Firebase に保存  
- 予約完了後、Toast メッセージで通知  

---

### 4. 予約履歴の確認
- [画面レイアウト](userImages/userselectbuslist.PNG)

- ユーザーが予約した内容をリストで確認可能  
- RecyclerView により予約リストを表示  
- 各予約アイテムに削除ボタンがあり、Firebase からリアルタイム削除可能  

---

### 5. その他の機能
- ログアウト機能  
- シンプルなデザインベースの静的UI構成  
- FAB（Floating Action Button）による補助機能：[レイアウト](userImages/userfab.PNG)  
- 予約の重複チェック機能なし（※本バージョンはリファクタリング前）

---

## ⚙️ 使用技術

| 項目 | 内容 |
|------|------|
| 言語 | Java |
| UI | Android XML |
| バックエンド | Firebase Firestore |
| 認証 | Firebase Authentication |
| 使用コンポーネント | Spinner、RecyclerView、Intent、Toast など |

---

> 🎓 本プロジェクトは、学生が自分の通学に使うスクールバスを手軽に予約できるように設計されたアプリです。  
> Firebaseを活用し、シンプルかつ実用的な構成で構築されています。

