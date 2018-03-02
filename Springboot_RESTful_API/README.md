# Springboot_RESTful_API

## 概要
下記情報を持つ商品データの登録・検索・変更・削除を行う、RESTfulなAPI  
レスポンスはJSON形式  
アクセスにはトークン認証が必要
- 商品画像
- 商品タイトル(最大100文字)
- 説明文(最大500文字)
- 価格

[設計資料](https://github.com/utgwn/ServerSide_Projects/wiki/Springboot_RESTful_API)

## 使用技術
- Java（1.8）
- Spring Boot（1.5.3）
- Spring JDBC（4.3.6）
- PostgreSQL（9.6.2）
- Redis (4.0.1)
- Maven（3.3.9）

## 起動方法
1. このリポジトリをダウンロードかCloneする
2. データベースのセットアップ
    - PostgreSQLをインストール
    - データベースを作成し、下記のテーブルを作成  
    `CREATE TABLE item (id serial PRIMARY KEY, title varchar(100), note varchar(500), image bytea);`
    - リポジトリの中の /src/main/resources/ にある application.properties の、データベースの名前、ユーザーネーム、パスワードを書き換える
    - Redisをインストール
3. ターミナルでルートディレクトリから次のコマンドを実行：`mvn install`
4. 起動
    - カレントディレクトリに移動　例：`cd Springboot_RESTful_API-master`
    - カレントディレクトリで次のコマンドを実行：`mvn spring-boot:run`
8. 次のURLからAPIを使用できます：`localhost:2222/`

## 認証手順
1. ターミナルからRedisを起動：`redis-server`
2. [SpringBoot_Twitter_OAuth](https://github.com/utgwn/ServerSide_Projects/tree/master/SpringBoot_Twitter_OAuth) を起動
3. 自分のTwitterアカウントでログイン
4. ログイン後の画面に表示されるトークンを確認
    - 例：8eb23b4aa55a605e272bd704d4df404f
5. ヘッダーにトークンをセットすることでリクエストできます
    - 例：`curl -H 'token:8eb23b4aa55a605e272bd704d4df404f' http://localhost:2222/items`

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
    - spring-jdbc（4.3.6.RELEASE）と postgresql (9.1-901.jdbc4) をライブラリに追加
    - DBアクセスするためのDriverManagerDataSourceをapplication.propertiesで定義
    - Redisをインストール
    - spring-boot-starter-redis (1.4.7.RELEASE) をライブラリに追加
