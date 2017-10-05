# SpringBoot_Twitter_OAuth

## 概要
OAuthを利用したTwitterのログイン・ログアウトシステム
- 認証後に表示
    - [Springboot_RESTful_API](https://github.com/utgwn/ServerSide_Projects/tree/master/Springboot_RESTful_API "")
の認証に必要なトークン
    - [SpringBoot_Totalization_Batch](https://github.com/utgwn/ServerSide_Projects/tree/master/SpringBoot_Totalization_Batch "")
で集計した商品情報

<img src="https://github.com/utgwn/ServerSide_Projects/blob/master/SpringBoot_Twitter_OAuth/screenshot/screenshot1.png" width="500">

- ログアウト後はログイン画面に遷移
- 認証せずに画面にアクセスされた場合は、専用のエラー画面（missingAuthorizationException.html）を表示  

## 使用技術
- Java（1.8）
- Spring Boot（1.5.3）
- Spring social twitter（1.1.2）
- Redis (4.0.1)
- Maven（3.3.9）
- Thymeleaf（spring-boot-starter-thymeleaf 1.5.4）

## 起動方法
1. このリポジトリをダウンロードかCloneする
2. Twitterのアプリケーション登録をして、コンシューマキーとコンシューマシークレットを取得
3. `/src/main/resources/application.properties` に取得したコンシューマキーとコンシューマシークレットを記入
4. ターミナルでルートディレクトリから次のコマンドを実行：`mvn install`
5. 起動
    - カレントディレクトリに移動　例：`cd SpringBoot_Twitter_OAuth-master`
    - カレントディレクトリで次のコマンドを実行：`mvn spring-boot:run`
6. 次のURLにアクセス：`localhost:1111/`

## 開発環境のセットアップ手順
- Java
    - JDK 8とEclipse（Version: Neon.2 Release (4.6.2)）をインストール
- Spring Boot
    - Eclipseにspring tool suiteプラグインをインストール（Spring Tool Suite (STS) for Eclipse 3.8.3 RELEASE）
    - プロジェクト作成
        - Spring Starter Projectを作成
        - 依存関係で「web」だけチェック入れる
