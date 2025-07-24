# REST API для получения данным по кошельку и изменения баланса кошелька.
## Стек: SPRING, POSTGRESQL, LIQUIBASE, DOCKER
## Эндпоинты

1. POST api/v1/wallet
{
walletId: UUID,
operationType: DEPOSIT or WITHDRAW,
amount: 1000
}

2. GET api/v1/wallets/{WALLET_UUID}

## Детали
Все эндпоинты покрыты тестами. Добавлено логирование.
Реализовано кэширование запросов для уменьшения времени обработки.
Миграции описаны в папке resourses/db/changelog.
Параметры настраиваются при помощи .env файла. Пример: `docker compose --env-file <path/to/your/env_file> up`
Предусмотрены ответы для невалидных запросов.

## Запуск приложения
`docker compose build`
Включает в себя сборку и тестирование.
`docker compose up`
Запускает два контейнера: PostgreSQL и само приложение.
`docker compose down`
Сворачивает контейнеры.
