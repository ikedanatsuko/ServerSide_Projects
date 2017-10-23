# SpringBoot_Totalization_Batch

## 概要
[Springboot_RESTful_API](https://github.com/utgwn/ServerSide_Projects/tree/master/Springboot_RESTful_API)
の商品テーブルから次の情報を日毎に集計するバッチ処理  
- 作成された商品数と商品情報
- 削除された商品数と商品情報

1日1度だけ、00:00 に起動  
集計結果は
[SpringBoot_Twitter_OAuth](https://github.com/utgwn/ServerSide_Projects/tree/master/SpringBoot_Twitter_OAuth)
のログイン後の画面に表示

[設計資料](https://github.com/utgwn/ServerSide_Projects/wiki/SpringBoot_Totalization_Batch)

## 使用技術
- Java（1.8）
- Spring Boot（1.5.3)
- Spring Batch  (3.0.8)
- Spring JDBC（4.3.6）
- PostgreSQL（9.6.2）
- Maven（3.3.9）

## 起動方法
1. このリポジトリをダウンロードかCloneする
2. データベースのセットアップ
    - PostgreSQLをインストール
    - データベースを作成し、テーブルを作成  
        - [テーブルの構成](https://github.com/utgwn/ServerSide_Projects/wiki/SpringBoot_Totalization_Batch#database)
    - リポジトリの中の /src/main/resources/ にある application.properties の、データベースの名前、ユーザーネーム、パスワードを書き換える
3. ターミナルでルートディレクトリから次のコマンドを実行：`mvn install`
4. 起動
    - カレントディレクトリに移動　例：`cd SpringBoot_Totalization_Batch-master`
    - カレントディレクトリで次のコマンドを実行：`mvn spring-boot:run`
    
## 開発環境のセットアップ手順
- Java
    - JDK 8 とEclipse（Version: Neon.2 Release (4.6.2)）をインストール
- Spring Boot
    - Eclipseにspring tool suiteプラグインをインストール（Spring Tool Suite (STS) for Eclipse 3.8.3 RELEASE）
    - プロジェクト作成
        - Spring Starter Projectを作成
        - 依存関係で「web」だけチェック入れる
- サーバ
    - [ここ](http://qiita.com/park-jh/items/08bb2541943f92e1feb1 "springの再入門 - eclipseでスタート")を参考にして Pivotal tc Server Developer Editionを設置
- データベース
    - PostgreSQLをインストール
    - spring-jdbc（4.3.6.RELEASE）をライブラリに追加
    - DBアクセスするためのDriverManagerDataSourceをapplication.propertiesで定義
