import { createLogger, transports, format } from "winston";

export const logger = createLogger({
  level: process.env.LOGLEVEL,
  transports: [new transports.Console()],
  format: format.combine(
    format.printf(({ level, message }) => {
      return `${level.toUpperCase()}: ${message}`;
    })
  ),
});
