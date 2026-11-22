
#!/usr/bin/env bash
set -euo pipefail

URL="${1:-http://localhost:8081/cours/health}"
TENTATIVES="${2:-20}"

for i in $(seq 1 "$TENTATIVES"); do
  if REPONSE=$(curl -fsS -m 3 "$URL"); then
    echo "OK : $URL répond -> $REPONSE"
    exit 0
  fi

  echo "Tentative $i/$TENTATIVES : service pas encore prêt..."
  sleep 3
done

echo "ERREUR : $URL ne répond pas après $TENTATIVES tentatives" >&2
exit 1
