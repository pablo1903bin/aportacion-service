#!/usr/bin/env bash
# ============================================
# Deploy aportacion-service (sin healthcheck)
# - Compila con Maven
# - Construye imagen Docker y la publica
# - Actualiza el servicio con Docker Compose
# ============================================

set -euo pipefail
trap 'echo "❌ Error en línea $LINENO. Abortando."; exit 1' ERR

# Uso: ./deploy-aportacion.sh [VERSION] [COMPOSE_DIR]
VERSION="${1:-v1.0.2}"
COMPOSE_DIR="${2:-$HOME/Documentos/Compose/aportacion-service}"

REGISTRY="pablitomixweb"
APP_NAME="aportacion-service"
IMAGE_NAME="$REGISTRY/$APP_NAME:$VERSION"

# Calcula una "versión anterior" sencilla para limpiar imagen local
VERSION_ANTERIOR=$(echo "$VERSION" | awk -F. '{
  if ($3 > 0)      { $3 -= 1 }
  else if ($2 > 0) { $2 -= 1; $3 = 9 }
  printf "%s.%s.%s", $1, $2, $3
}')
IMAGE_ANTERIOR="$REGISTRY/$APP_NAME:$VERSION_ANTERIOR"

echo "ℹ️  Servicio:           $APP_NAME"
echo "ℹ️  Versión objetivo:   $VERSION"
echo "ℹ️  Imagen objetivo:    $IMAGE_NAME"
echo "ℹ️  Compose dir:        $COMPOSE_DIR"
echo

# 1) Build Maven
echo "🔧 Paso 1: mvn clean package (skipTests) ..."
cd "$(dirname "$0")"
mvn -q -DskipTests clean package

# 2) Docker build
echo "🐳 Paso 2: docker build $IMAGE_NAME ..."
DOCKER_BUILDKIT=1 docker build -t "$IMAGE_NAME" .

# 3) Push
echo "📤 Paso 3: docker push ..."
docker push "$IMAGE_NAME"

# 4) Deploy con compose (sin --wait)
echo "📦 Paso 4: Deploy con docker compose en $COMPOSE_DIR ..."
cd "$COMPOSE_DIR"

# Si tu docker-compose usa: image: pablitomixweb/aportacion-service:${IMAGE_TAG}
export IMAGE_TAG="$VERSION"

echo "📥 docker compose pull aportacion-service ..."
docker compose pull aportacion-service

echo "🚀 docker compose up -d aportacion-service ..."
docker compose up -d aportacion-service

# 5) Limpieza de imagen local anterior (best effort)
echo "🧹 Paso 5: limpiar imagen local anterior $IMAGE_ANTERIOR ..."
docker image rm "$IMAGE_ANTERIOR" 2>/dev/null || echo "ℹ️  No se eliminó $IMAGE_ANTERIOR (no existe/está en uso)."

# 6) Estado y logs breves
echo
echo "🔎 Estado del servicio:"
docker compose ps aportacion-service || true
echo
echo "📜 Últimos 120 logs:"
docker compose logs --tail=120 aportacion-service || true

echo
echo "✅ Deploy completado. Imagen en ejecución: $IMAGE_NAME"
echo "💡 Logs en vivo: docker compose logs -f aportacion-service"
